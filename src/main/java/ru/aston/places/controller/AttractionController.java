package ru.aston.places.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public AttractionFullResponse createAttraction(
            @Validated @RequestBody AttractionNewRequest dto
            ) {
        log.info("Обработка эндпойнта POST/attraction/.(body: AttractionNewRequest)");
        return attractionService.create(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AttractionShortResponse> getAttractions(
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "filter", required = false) String filter,
            @RequestParam(name = "location", required = false) String location
    ) {
        log.info("Обработка эндпойнта GET/attraction?sort={}&filter={}&location={}.",
            sort, filter, location);
        AttractionParameters parameters = AttractionParameters.builder()
                .sort(sort)
                .filter(filter)
                .location(location)
                .build();
        return attractionService.findAttractionsWith(parameters);
    }

    @PatchMapping ("/{attractionId}")
    @ResponseStatus(HttpStatus.OK)
    public AttractionFullResponse updateAttraction(
            @PathVariable Long attractionId,
            @Validated @RequestBody AttractionUpdateRequest dto
    ) {
        log.info("Обработка эндпойнта PATCH/attraction/{}.(body: AttractionUpdateRequest)",
            attractionId);
        return attractionService.update(attractionId, dto);
    }

    @DeleteMapping("/{attractionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAttraction(
            @PathVariable Long attractionId
    ) {
        log.info("Обработка эндпойнта DELETE/attraction/{}.", attractionId);
        attractionService.delete(attractionId);
    }
}
