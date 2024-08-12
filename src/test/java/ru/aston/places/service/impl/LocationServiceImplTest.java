package ru.aston.places.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.places.MockDto;
import ru.aston.places.MockEntity;
import ru.aston.places.controller.dto.LocationFullResponse;
import ru.aston.places.controller.dto.LocationNewRequest;
import ru.aston.places.model.Location;
import ru.aston.places.repository.LocationRepository;

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

  @Mock
  private LocationRepository locationRepository;

  @InjectMocks
  private LocationServiceImpl locationService;

  @Test
  void create() {
    Location location = MockEntity.newLocation;

    Location locationCreated = MockEntity.createdLocation;

    when(locationRepository.save(location)).thenReturn(locationCreated);

    LocationNewRequest locationNewRequest = MockDto.locationNewRequest3;

    LocationFullResponse expected = MockDto.createdLocationFullResponse;

    LocationFullResponse actual = locationService.create(locationNewRequest);

    assertEquals(expected, actual);
  }
}