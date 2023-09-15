package com.saurs.swapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurs.swapi.models.Planet;

import java.util.List;
import java.util.UUID;

public interface PlanetRepository extends JpaRepository<Planet, UUID> {
  List<Planet> findByName(String name);
}
