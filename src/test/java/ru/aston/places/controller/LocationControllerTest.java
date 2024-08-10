package ru.aston.places.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.aston.places.MockDto;
import ru.aston.places.controller.dto.LocationFullResponse;
import ru.aston.places.controller.dto.LocationNewRequest;
import ru.aston.places.controller.dto.LocationUpdateRequest;
import ru.aston.places.service.LocationService;

@WebMvcTest(LocationController.class)
class LocationControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private LocationService locationService;

  @Test
  void createLocation() throws Exception {

    LocationNewRequest locationNewRequest = MockDto.locationNewRequest1;

    LocationFullResponse locationFullResponse = MockDto.locationFullResponse1;

    when(locationService.create(locationNewRequest)).thenReturn(locationFullResponse);

    mockMvc.perform(post("/location")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(locationNewRequest)))
        .andExpect(status().isCreated())
        .andExpect(content().json(objectMapper.writeValueAsString(locationFullResponse)));
  }

  @Test
  void updateLocation() throws Exception {
    LocationUpdateRequest locationUpdateRequest = MockDto.locationUpdateRequest;
    LocationFullResponse updatedLocationFullResponse = MockDto.updatedLocationFullResponse;

    when(locationService.update(updatedLocationFullResponse.getId(), locationUpdateRequest))
        .thenReturn(updatedLocationFullResponse);

    mockMvc.perform(patch("/location/" + updatedLocationFullResponse.getId())
    .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(locationUpdateRequest)))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(updatedLocationFullResponse)));
  }
}