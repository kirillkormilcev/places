package ru.aston.places.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.aston.places.model.Location;
import ru.aston.places.service.LocationService;

@Slf4j
@RestController
@RequestMapping("/location")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Validated
public class LocationController {
    final LocationService locationService;

    @PostMapping
    public ResponseEntity<Location> createLocation(@Validated @RequestBody Location location) {
        return null;
    }

}
