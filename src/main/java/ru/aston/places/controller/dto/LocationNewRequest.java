package ru.aston.places.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Builder
@EqualsAndHashCode
public class LocationNewRequest {
    @NotNull
    @NotBlank
    String name;
    @Positive
    @Min(10000L)
    Long population;
    @NotNull
    Boolean hasMetro;
    @EqualsAndHashCode.Exclude
    List<AttractionNewRequest> attractions;
}
