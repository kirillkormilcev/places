package ru.aston.places.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import ru.aston.places.MockDto;
import ru.aston.places.MockEntity;
import ru.aston.places.controller.dto.AttractionShortResponse;
import ru.aston.places.model.Attraction;
import ru.aston.places.model.AttractionParameters;
import ru.aston.places.service.AttractionService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AttractionRepositoryTest {

  @Container
  public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
      DockerImageName.parse("postgres:14.4-alpine"))
      .withDatabaseName("test")
      .withUsername("test")
      .withPassword("test");

  @Autowired
  private AttractionRepository attractionRepository;

  @Autowired
  private LocationRepository locationRepository;

  @Autowired
  private AttractionService attractionService;

  @Test
  void save() {
    Attraction expected = MockEntity.updatedAttraction;
    expected.setId(1L);

    locationRepository.save(MockEntity.location1);

    Attraction actual = attractionRepository.save(MockEntity.newAttraction);

    assertEquals(expected, actual);
  }

  @Test
  void deleteById() {
    locationRepository.save(MockEntity.location1);
    attractionRepository.save(MockEntity.newAttraction);
    attractionRepository.deleteById(1L);
    assertFalse(attractionRepository.findById(1L).isPresent());
  }

  @Test
  void findAttractionsWithParameters() {
    locationRepository.save(MockEntity.location1);
    locationRepository.save(MockEntity.location2);
    attractionRepository.save(MockEntity.attraction1);
    attractionRepository.save(MockEntity.attraction2);
    attractionRepository.save(MockEntity.newAttraction);

    String sort = "NAME";
    String filter = "MUSEUM";
    String location = "Moscow";
    AttractionParameters parameters = AttractionParameters.builder()
        .sort(sort)
        .filter(filter)
        .location(location)
        .build();

    List<AttractionShortResponse> expected = MockDto.attractionsMuseumForMoscow;

    List<AttractionShortResponse> actual = attractionService.findAttractionsWith(parameters);

    assertEquals(expected, actual);
  }
}