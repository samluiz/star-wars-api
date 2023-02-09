package com.saurs.swapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurs.swapi.models.Planet;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
  Planet findByName(String name);
}
