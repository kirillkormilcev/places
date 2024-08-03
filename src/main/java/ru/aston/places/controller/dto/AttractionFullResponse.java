package ru.aston.places.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.aston.places.model.AttractionType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AttractionFullResponse {
    Long id;
    String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate created;
    String description;
    AttractionType attractionType;
    LocationShortResponse location;
    List<ServiceShortResponse> services;
}
