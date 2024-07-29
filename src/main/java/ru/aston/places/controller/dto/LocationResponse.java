package ru.aston.places.controller.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LocationResponse {
    Long id;
    String name;
    Long population;
    Boolean hasMetro;
    List<AttractionShortResponse> attractions;
}
