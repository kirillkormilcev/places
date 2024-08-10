package ru.aston.places.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.aston.places.model.AttractionType;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Builder
@EqualsAndHashCode
public class AttractionNewRequest {
    @NotNull
    @NotBlank
    String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate created;
    @NotNull
    @NotBlank
    String description;
    @NotNull
    AttractionType attractionType;
    @EqualsAndHashCode.Exclude
    Long locationId;
}
