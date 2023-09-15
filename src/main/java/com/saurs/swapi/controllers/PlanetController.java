package com.saurs.swapi.controllers;

import com.saurs.swapi.models.Planet;
import com.saurs.swapi.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/planets")
public class PlanetController {

    PlanetService service;

    @Autowired
    public PlanetController(PlanetService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Planet> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Planet findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Planet> findByName(@RequestParam String name) {
        return service.findByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Planet addPlanet(@RequestBody Planet obj) {
        return service.save(obj);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Planet updatePlanet(@RequestBody Planet obj, @PathVariable UUID id) {
        return service.update(obj, id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Planet partialUpdatePlanet(@RequestBody Planet obj, @PathVariable UUID id) {
        return service.patch(obj, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlanet(@PathVariable UUID id) {
        service.delete(id);
    }

}
