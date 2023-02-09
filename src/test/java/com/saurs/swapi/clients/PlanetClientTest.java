package com.saurs.swapi.clients;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.saurs.swapi.models.Planet;

@SpringBootTest
public class PlanetClientTest {

  @Autowired
  private PlanetClient planetClient;

  @Test
  public void getPlanetDataFromAPI() {
    Planet planet = planetClient.getPlanet(1);
    
    assertEquals(planet.getName(), "Tatooine");
  }
  
}
