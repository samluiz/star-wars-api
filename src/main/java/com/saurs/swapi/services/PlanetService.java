package com.saurs.swapi.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.saurs.swapi.clients.PlanetClient;
import com.saurs.swapi.models.Planet;
import com.saurs.swapi.repositories.PlanetRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PlanetService {
  
  private PlanetRepository repository;
  private PlanetClient client;

  public PlanetService(PlanetRepository repository, @Lazy PlanetClient client) {
    this.repository = repository;
    this.client = client;
  }

  public void loadPlanets(Integer size) {
    Set<Planet> planets = new HashSet<>();
    for (int i = 1; i <= size; i++) {
      planets.add(client.getPlanet(i));
    }
    repository.saveAll(planets);
  }

  public List<Planet> findAll() {
    return repository.findAll();
  } 

  public Planet findById(Long id) {
    return repository.findById(id).orElseThrow();
  }

  public void saveAll(List<Planet> planets) {
    repository.saveAll(planets);
  }

  public Planet save(Planet planet) {
    return repository.save(planet);
  }

  public Planet update(Planet planet, Long id) {
    Planet newPlanet = repository.getReferenceById(id);
    newPlanet.setName(planet.getName());
    newPlanet.setClimate(planet.getClimate());
    newPlanet.setTerrain(planet.getTerrain());
    return repository.save(newPlanet);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}
