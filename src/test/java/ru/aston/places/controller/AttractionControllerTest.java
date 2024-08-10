package ru.aston.places.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.aston.places.MockDto;
import ru.aston.places.controller.dto.AttractionFullResponse;
import ru.aston.places.controller.dto.AttractionNewRequest;
import ru.aston.places.controller.dto.AttractionShortResponse;
import ru.aston.places.controller.dto.AttractionUpdateRequest;
import ru.aston.places.model.AttractionParameters;
import ru.aston.places.service.AttractionService;

@WebMvcTest(AttractionController.class)
class AttractionControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private AttractionService attractionService;

  @Test
  void createAttraction() throws Exception {

    AttractionNewRequest attractionNewRequest = MockDto.attractionNewRequest1;
    AttractionFullResponse attractionFullResponse = MockDto.attractionFullResponse1;

    when(attractionService.create(attractionNewRequest)).thenReturn(attractionFullResponse);

    mockMvc.perform(post("/attraction")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(attractionNewRequest)))
        .andExpect(status().isCreated())
        .andExpect(content().json(objectMapper.writeValueAsString(attractionFullResponse)));
  }

  @Test
  void getAttractions() throws Exception {
    List<AttractionShortResponse> attractionsMuseumForMoscow = MockDto.attractionsMuseumForMoscow;
    String sort = "NAME";
    String filter = "MUSEUM";
    String location = "Moscow";
    AttractionParameters parameters = AttractionParameters.builder()
        .sort(sort)
        .filter(filter)
        .location(location)
        .build();

    when(attractionService.findAttractionsWith(parameters)).thenReturn(attractionsMuseumForMoscow);

    mockMvc.perform(get(
            String.format("/attraction?location=%s&filter=%s&sort=%s", location, filter, sort))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
        //.andExpect(content().json(objectMapper.writeValueAsString(attractionsMuseumForMoscow)));
  }

  @Test
  void updateAttraction() throws Exception {
    AttractionUpdateRequest attractionUpdateRequest = MockDto.attractionUpdateRequest;
    AttractionFullResponse updatedAttractionFullResponse = MockDto.updatedAttractionFullResponse;

    when(attractionService.update(updatedAttractionFullResponse.getId(), attractionUpdateRequest))
        .thenReturn(updatedAttractionFullResponse);

    mockMvc.perform(patch("/attraction/" + updatedAttractionFullResponse.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(attractionUpdateRequest)))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(updatedAttractionFullResponse)));
  }

  @Test
  void deleteAttraction() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/attraction/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }
}