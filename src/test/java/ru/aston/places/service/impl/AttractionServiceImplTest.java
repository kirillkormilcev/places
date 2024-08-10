package ru.aston.places.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.places.MockDto;
import ru.aston.places.MockEntity;
import ru.aston.places.controller.dto.AttractionFullResponse;
import ru.aston.places.controller.dto.AttractionNewRequest;
import ru.aston.places.model.Attraction;
import ru.aston.places.repository.AttractionRepository;
import ru.aston.places.repository.LocationRepository;

@ExtendWith(MockitoExtension.class)
class AttractionServiceImplTest {

  @Mock
  private AttractionRepository attractionRepository;

  @Mock
  private LocationRepository locationRepository;

  @InjectMocks
  private AttractionServiceImpl attractionService;

  @Test
  void create() {
    Attraction attraction = MockEntity.newAttraction;
    attraction.setLocation(null);
    attraction.setId(null);

    Attraction attractionCreated = MockEntity.updatedAttraction;
    attractionCreated.setId(1L);

    when(attractionRepository.save(attraction)).thenReturn(attractionCreated);

    AttractionNewRequest attractionNewRequest = MockDto.attractionNewRequest3;

    AttractionFullResponse expected = MockDto.attractionFullResponse3;

    AttractionFullResponse actual = attractionService.create(attractionNewRequest);

    assertEquals(expected, actual);
  }

  @Test
  void delete() {
    attractionRepository.save(MockEntity.attraction1);

    AttractionFullResponse attractionFullResponse = MockDto.attractionFullResponse1;

    when(attractionRepository.findById(MockEntity.attraction1.getId())).thenReturn(
        Optional.of(MockEntity.attraction1));
    doNothing().when(attractionRepository).deleteById(attractionFullResponse.getId());

    attractionService.delete(attractionFullResponse.getId());

    verify(attractionRepository, times(1))
        .deleteById(attractionFullResponse.getId());
  }
}