package com.saurs.swapi.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.saurs.swapi.models.Planet;

@FeignClient(url = "https://swapi.dev/api", name = "planets")
public interface PlanetClient {

  @GetMapping("/planets/{id}")
  Planet getPlanet(@PathVariable Integer id);

}
