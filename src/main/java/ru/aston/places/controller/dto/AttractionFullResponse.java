package ru.aston.places.controller.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.aston.places.model.AttractionType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AttractionFullResponse {
    Long id;
    String name;
    LocalDate created;
    String description;
    AttractionType attractionType;
    LocationShortResponse location;
    List<ServiceShortResponse> services;
}
