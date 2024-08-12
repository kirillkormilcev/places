package ru.aston.places;

import java.time.LocalDate;
import java.util.List;
import ru.aston.places.controller.dto.AttractionFullResponse;
import ru.aston.places.controller.dto.AttractionNewRequest;
import ru.aston.places.controller.dto.AttractionShortResponse;
import ru.aston.places.controller.dto.AttractionUpdateRequest;
import ru.aston.places.controller.dto.LocationFullResponse;
import ru.aston.places.controller.dto.LocationNewRequest;
import ru.aston.places.controller.dto.LocationUpdateRequest;
import ru.aston.places.controller.mapper.AttractionMapper;
import ru.aston.places.controller.mapper.LocationMapper;
import ru.aston.places.model.AttractionType;

public class MockDto {
  private MockDto() {}

  public static final AttractionNewRequest attractionNewRequest1 =
      AttractionNewRequest.builder()
          .name("Kremlin of Kazan")
          .created(LocalDate.of(1994, 1, 22))
          .description("Казанский кремль.")
          .attractionType(AttractionType.PALACE)
          .locationId(1L)
          .build();

  public static final AttractionNewRequest attractionNewRequest3 =
      AttractionNewRequest.builder()
          .name("Gorky Park")
          .created(LocalDate.of(1939, 5, 24))
          .description("Центральный парк культуры и отдыха имени М. Горького.")
          .attractionType(AttractionType.PARK)
          .locationId(1L)
          .build();

  public static final AttractionUpdateRequest attractionUpdateRequest =
      AttractionUpdateRequest.builder()
          .description("Центральный парк культуры и отдыха имени М. Горького.")
          .build();

  public static final AttractionFullResponse updatedAttractionFullResponse =
      AttractionMapper.INSTANCE.attractionToAttractionFullResponse(MockEntity.updatedAttraction);

  public static final AttractionFullResponse attractionFullResponse1 =
      AttractionMapper.INSTANCE.attractionToAttractionFullResponse(MockEntity.attraction1);

  public static final AttractionFullResponse attractionFullResponse2 =
      AttractionMapper.INSTANCE.attractionToAttractionFullResponse(MockEntity.attraction2);

  public static final AttractionFullResponse attractionFullResponse3 =
      AttractionMapper.INSTANCE.attractionToAttractionFullResponse(MockEntity.updatedAttraction);

  public static final AttractionShortResponse attractionShortResponse1 =
      AttractionMapper.INSTANCE.attractionToAttractionShortResponse(MockEntity.attraction1);

  public static final AttractionShortResponse attractionShortResponse2 =
      AttractionMapper.INSTANCE.attractionToAttractionShortResponse(MockEntity.attraction2);

  public static final AttractionShortResponse attractionShortResponse3 =
      AttractionMapper.INSTANCE.attractionToAttractionShortResponse(MockEntity.updatedAttraction);

  public static final List<AttractionShortResponse> attractionsMuseumForMoscow =
      List.of(attractionShortResponse2);

  public static final LocationNewRequest locationNewRequest1 =
      LocationNewRequest.builder()
          .name("Kazan")
          .population(1_300_000L)
          .hasMetro(true)
          .build();

  public static final LocationNewRequest locationNewRequest3 =
      LocationNewRequest.builder()
          .name("Ufa")
          .population(1_100_000L)
          .hasMetro(false)
          .build();

  public static final LocationFullResponse locationFullResponse1 =
      LocationMapper.INSTANCE.locationToLocationFullResponse(MockEntity.location1);

  public static final LocationFullResponse createdLocationFullResponse =
      LocationMapper.INSTANCE.locationToLocationFullResponse(MockEntity.createdLocation);

  public static final LocationUpdateRequest locationUpdateRequest =
      LocationUpdateRequest.builder()
          .population(1_100_000L)
          .hasMetro(true)
          .build();

  public static final LocationFullResponse updatedLocationFullResponse =
      LocationMapper.INSTANCE.locationToLocationFullResponse(MockEntity.updatedLocation);
}
