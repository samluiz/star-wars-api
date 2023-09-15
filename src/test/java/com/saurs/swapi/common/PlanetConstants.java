package com.saurs.swapi.common;

import com.saurs.swapi.models.Planet;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PlanetConstants {
    public static final Planet PLANET = new Planet("Viena", "desert", "mountains");
    public static final Planet NEW_PLANET =  new Planet("Samucoide", "arid", "forest");
    public static final Planet INVALID_PLANET = new Planet("", "", "");
    public static final List<Planet> PLANETS = Arrays.asList(PLANET, NEW_PLANET);
    public static final UUID RANDOM_UUID = UUID.randomUUID();
}
