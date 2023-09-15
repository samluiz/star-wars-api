package com.saurs.swapi.services;

import com.saurs.swapi.exceptions.services.CannotBeNullException;
import com.saurs.swapi.exceptions.services.ResourceNotFoundException;
import com.saurs.swapi.models.Planet;
import com.saurs.swapi.repositories.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.saurs.swapi.common.PlanetConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

  @InjectMocks
  private PlanetService planetService;

  @Mock
  private PlanetRepository planetRepository;

  @Test
  public void createPlanet_WithValidData_AndThenReturn() {
    when(planetRepository.save(PLANET)).thenReturn(PLANET);

    Planet createdPlanet = planetService.save(PLANET);

    assertThat(createdPlanet).isEqualTo(PLANET);
  }

  @Test
  public void createPlanet_InvalidData_ThrowException() {
    assertThatThrownBy(() -> planetService.save(INVALID_PLANET)).isInstanceOf(CannotBeNullException.class);
  }

  @Test
  public void findAllPlanets_ReturnList() {
    when(planetRepository.findAll()).thenReturn(PLANETS);

    List<Planet> planets = planetService.findAll();

    assertThat(planets.size()).isGreaterThan(0);
    assertThat(planets).hasSize(2);
    assertThat(planets).isEqualTo(PLANETS);
  }

  @Test
  public void findPlanetById_AndThenReturn() {
    when(PLANET.getUuid()).thenReturn(RANDOM_UUID);
    when(planetRepository.findById(PLANET.getUuid())).thenReturn(Optional.of(PLANET));

    Planet foundPlanet = planetService.findById(PLANET.getUuid());

    assertThat(foundPlanet).isEqualTo(PLANET);
  }

  @Test
  public void findPlanetById_ThrowException() {
    assertThrows(ResourceNotFoundException.class, () -> planetRepository.findById(RANDOM_UUID)
            .orElseThrow(() -> new ResourceNotFoundException(RANDOM_UUID)));
  }

  @Test
  public void updatePlanet_andThenReturn() {
    when(planetRepository.save(NEW_PLANET)).thenReturn(NEW_PLANET);
    when(PLANET.getUuid()).thenReturn(RANDOM_UUID);

    Planet newPlanet = planetService.update(NEW_PLANET, PLANET.getUuid());

    assertThat(newPlanet).isEqualTo(NEW_PLANET);
  }
  
}
