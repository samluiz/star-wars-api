package com.saurs.swapi.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.saurs.swapi.models.Planet;
import com.saurs.swapi.repositories.PlanetRepository;

@SpringBootTest
public class PlanetServiceTest {

  @Autowired
  private PlanetRepository planetRepository;

  @Autowired
  private PlanetService planetService;

  @BeforeEach
  public void setUp() {
    planetRepository.deleteAll();
    System.out.println("Database cleared");
  }
  
  @AfterEach
  public void cleanUp() {
    planetRepository.deleteAll();
    System.out.println("Database cleared");
  }

  @Test
  public void mustLoadTwentyPlanetsToDatabase() {
    planetService.loadPlanets(20);
    Long count = planetRepository.count();
    assertEquals(count, 20);
  }

  @Test
  public void mustInsertPlanet() {
    Planet planet = new Planet(null, "Janouvis", "desert", "hot");
    Planet newPlanet = planetService.save(planet);
    assertEquals(newPlanet.getName(),"Janouvis");
  }

  @Nested
  class TestsThatNeedEntities {
    @BeforeEach
    public void setUp() {
      Planet planet = new Planet(null, "Janouvis", "desert", "hot");
      planetService.save(planet);
      System.out.println("Planet created");
    }

    @AfterEach
    public void cleanUp() {
      planetRepository.deleteAll();
      System.out.println("Database cleared");
    }

    @Test
    public void mustGetAllPlanets() {
      List<Planet> planets = planetService.findAll();
      assertTrue(planets.size() > 0);
    }
    @Test
    public void mustUpdatePlanet() {
      Planet planet = planetRepository.findByName("Janouvis");
      planet.setName("Viena");
      Planet newPlanet = planetService.update(planet, planet.getId());
      assertEquals(newPlanet.getName(), "Viena");
    }

    @Test
    public void mustDeletePlanet() {
      Planet planet = planetRepository.findByName("Janouvis");
      planetService.delete(planet.getId());
      Long count = planetRepository.count();
      assertEquals(count, 0);
    }
  }

  
  
  
}
