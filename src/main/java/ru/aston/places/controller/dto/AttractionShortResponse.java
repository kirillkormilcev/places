package ru.aston.places.controller.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.aston.places.model.AttractionType;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AttractionShortResponse {
    String name;
    AttractionType attractionType;
}
