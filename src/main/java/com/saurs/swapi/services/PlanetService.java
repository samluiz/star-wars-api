package com.saurs.swapi.services;

import com.saurs.swapi.clients.PlanetClient;
import com.saurs.swapi.exceptions.services.CannotBeNullException;
import com.saurs.swapi.exceptions.services.InvalidUUIDException;
import com.saurs.swapi.exceptions.services.ResourceNotFoundException;
import com.saurs.swapi.models.Planet;
import com.saurs.swapi.models.PlanetList;
import com.saurs.swapi.repositories.PlanetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PlanetService {
  
  private PlanetRepository repository;
  private PlanetClient client;

  @Autowired
  public PlanetService(PlanetRepository repository, @Lazy PlanetClient client) {
    this.repository = repository;
    this.client = client;
  }

  public void loadPlanets() {
    PlanetList response = client.getPlanets();
    repository.saveAll(response.getResults());
  }

  public List<Planet> findAll() {
    return repository.findAll();
  } 

  public Planet findById(UUID id) {
    try {
      return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    } catch (MethodArgumentTypeMismatchException e) {
        throw new InvalidUUIDException(id);
    }
  }

  public List<Planet> findByName(String name) {
    return repository.findByName(name);
  }

  public Planet save(Planet planet) {
    if (planet.getName() == null || planet.getClimate() == null || planet.getTerrain() == null
    || planet.getName().isBlank() || planet.getClimate().isBlank() || planet.getTerrain().isBlank()) {
      throw new CannotBeNullException("A field can't be null or blank in this method");
    }
    return repository.save(planet);
  }

  public Planet update(Planet planet, UUID id) {
    try {
      Planet newPlanet = repository.getReferenceById(id);
      if (planet.getName() == null || planet.getClimate() == null || planet.getTerrain() == null
              || planet.getName().isBlank() || planet.getClimate().isBlank() || planet.getTerrain().isBlank()) {
        throw new CannotBeNullException("A field can't be null in this method");
      }
      newPlanet.setName(planet.getName());
      newPlanet.setClimate(planet.getClimate());
      newPlanet.setTerrain(planet.getTerrain());
      return repository.save(newPlanet);
    } catch (EmptyResultDataAccessException e) {
        throw new ResourceNotFoundException(id);
    }
  }

  public Planet patch(Planet planet, UUID id) {
    try {
      Planet newPlanet = repository.getReferenceById(id);
      newPlanet.setName(planet.getName() != null ? planet.getName() : newPlanet.getName());
      newPlanet.setClimate(planet.getClimate() != null ? planet.getClimate() : newPlanet.getClimate());
      newPlanet.setTerrain(planet.getTerrain() != null ? planet.getTerrain() : newPlanet.getTerrain());
      return repository.save(newPlanet);
    } catch (DataAccessException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  public void delete(UUID id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException(id);
    }
  }
}
