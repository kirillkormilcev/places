package ru.aston.places.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.aston.places.controller.dto.AttractionFullResponse;
import ru.aston.places.controller.dto.AttractionNewRequest;
import ru.aston.places.controller.dto.AttractionShortResponse;
import ru.aston.places.controller.dto.AttractionUpdateRequest;
import ru.aston.places.model.AttractionParameters;
import ru.aston.places.service.AttractionService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/attraction")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Validated
public class AttractionController {
    final AttractionService attractionService;

    @PostMapping
    public ResponseEntity<AttractionFullResponse> createAttraction(
            @Validated @RequestBody AttractionNewRequest dto
            ) {
        log.info("Обработка эндпойнта POST/attraction/.(body: AttractionNewRequest)");
        return new ResponseEntity<>(attractionService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AttractionShortResponse>> getAttractions(
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "filter", required = false) String filter,
            @RequestParam(name = "location", required = false) String location
    ) {
        log.info("Обработка эндпойнта GET/attraction?sort={}&filter={}&location={}.", sort, filter, location);
        AttractionParameters parameters = AttractionParameters.builder()
                .sort(sort)
                .filter(filter)
                .location(location)
                .build();
        return new ResponseEntity<>(attractionService.findAttractionsWith(parameters), HttpStatus.OK);
    }

    @PatchMapping ("/{attractionId}")
    public ResponseEntity<AttractionFullResponse> updateAttraction(
            @PathVariable Long attractionId,
            @Validated @RequestBody AttractionUpdateRequest dto
    ) {
        log.info("Обработка эндпойнта PATCH/attraction/{}.(body: AttractionUpdateRequest)", attractionId);
        return new ResponseEntity<>(attractionService.update(attractionId, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{attractionId}")
    public HttpStatus deleteAttraction(
            @PathVariable Long attractionId
    ) {
        log.info("Обработка эндпойнта DELETE/attraction/{}.", attractionId);
        attractionService.delete(attractionId);
        return HttpStatus.NO_CONTENT;
    }
}
