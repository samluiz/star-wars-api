package com.saurs.swapi.config;

import org.springframework.stereotype.Component;

import com.saurs.swapi.services.PlanetService;

import jakarta.annotation.PostConstruct;

@Component
public class LoadPlanets {

private PlanetService service;

public LoadPlanets(PlanetService service) {
  this.service = service;
}

@PostConstruct
public void init() {
  service.loadPlanets(20);
}

}
