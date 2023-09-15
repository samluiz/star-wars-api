package com.saurs.swapi.clients;

import com.saurs.swapi.models.Planet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PlanetClientTest {

  @Mock
  private PlanetClient planetClient;

  @Test
  public void getPlanetDataFromAPI() {

  }
  
}
