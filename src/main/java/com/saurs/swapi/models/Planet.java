package com.saurs.swapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Planet {

  @Id
  @UuidGenerator
  private UUID uuid;
  private String name;
  private String climate;
  private String terrain;


    public Planet(String name, String climate, String terrain) {
      this.name = name;
      this.climate = climate;
      this.terrain = terrain;
    }
}
