package com.saurs.swapi.models;

import lombok.Data;

import java.util.Collection;

@Data
public class PlanetList {
    private Collection<Planet> results;
}
