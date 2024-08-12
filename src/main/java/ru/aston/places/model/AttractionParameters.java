package ru.aston.places.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Parameters for Attraction
 * parametrized Request
 */
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttractionParameters {
    String sort;
    String filter;
    String location;
}
