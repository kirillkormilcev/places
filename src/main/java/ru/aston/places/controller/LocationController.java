package ru.aston.places.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.aston.places.controller.dto.LocationNewRequest;
import ru.aston.places.controller.dto.LocationFullResponse;
import ru.aston.places.controller.dto.LocationUpdateRequest;
import ru.aston.places.service.impl.LocationServiceImpl;

@Slf4j
@RestController
@RequestMapping("/location")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Validated
public class LocationController {
    final LocationServiceImpl locationService;

    @PostMapping
    public ResponseEntity<LocationFullResponse> createLocation(
            @Validated @RequestBody LocationNewRequest dto) {
        log.info("Обработка эндпойнта POST/location/.(body: LocationNewRequest)");
        return new ResponseEntity<>(locationService.create(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{locationId}")
    public ResponseEntity<LocationFullResponse> updateLocation(
            @PathVariable Long locationId,
            @Validated @RequestBody LocationUpdateRequest dto) {
        log.info("Обработка эндпойнта PATCH/location/{}.(body: LocationUpdateRequest)", locationId);
        return  new ResponseEntity<>(locationService.update(locationId, dto), HttpStatus.OK);
    }
}
