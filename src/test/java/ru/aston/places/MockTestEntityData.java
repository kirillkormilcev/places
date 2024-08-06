package ru.aston.places;

import java.time.LocalDate;
import ru.aston.places.model.Attraction;
import ru.aston.places.model.AttractionType;
import ru.aston.places.model.Location;

public class MockTestEntityData {

  private MockTestEntityData() {}

  public static final Location location1 = Location.builder()
      .id(1L)
      .name("Kazan")
      .population(1_300_000L)
      .hasMetro(true)
      .build();

  public static final Location location2 = Location.builder()
      .id(2L)
      .name("Moscow")
      .population(13_000_000L)
      .hasMetro(true)
      .build();

  public static final Location newLocation = Location.builder()
      .name("Ufa")
      .population(1_100_000L)
      .hasMetro(false)
      .build();

  public static final Location createdLocation = Location.builder()
      .id(3L)
      .name("Ufa")
      .population(1_100_000L)
      .hasMetro(false)
      .build();

  public static final Location updatedLocation = Location.builder()
      .id(3L)
      .name("Ufa")
      .population(1_100_000L)
      .hasMetro(true)
      .build();

  public static final Attraction attraction1 = Attraction.builder()
      .id(1L)
      .name("Kremlin of Kazan")
      .created(LocalDate.of(1994, 1, 22))
      .description("Казанский кремль.")
      .attractionType(AttractionType.PALACE)
      .location(location1)
      .build();

  public static final Attraction attraction2 = Attraction.builder()
      .id(2L)
      .name("VDNKH")
      .created(LocalDate.of(1939, 8, 1))
      .description("Выставка достижений народного хозяйства.")
      .attractionType(AttractionType.MUSEUM)
      .location(location2)
      .build();

  public static final Attraction newAttraction = Attraction.builder()
      .name("Gorky Park")
      .created(LocalDate.of(1939, 5, 24))
      .description("Центральный парк культуры и отдыха имени М. Горького.")
      .attractionType(AttractionType.PARK)
      .location(location1)
      .build();

  public static final Attraction createdAttraction = Attraction.builder()
      .id(3L)
      .name("Gorky Park")
      .created(LocalDate.of(1939, 5, 24))
      .description("Горький парк.")
      .attractionType(AttractionType.PARK)
      .location(location1)
      .build();

  public static final Attraction updatedAttraction = Attraction.builder()
      .id(3L)
      .name("Gorky Park")
      .created(LocalDate.of(1939, 5, 24))
      .description("Центральный парк культуры и отдыха имени М. Горького.")
      .attractionType(AttractionType.PARK)
      .location(location1)
      .build();
}
