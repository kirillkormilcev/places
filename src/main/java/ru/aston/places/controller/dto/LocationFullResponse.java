package ru.aston.places.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode
public class LocationFullResponse {
    Long id;
    String name;
    Long population;
    Boolean hasMetro;
    @EqualsAndHashCode.Exclude
    List<AttractionShortResponse> attractions;
}
