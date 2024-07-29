package ru.aston.places.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.aston.places.controller.dto.AttractionFullResponse;
import ru.aston.places.controller.dto.AttractionNewRequest;
import ru.aston.places.controller.dto.AttractionShortResponse;
import ru.aston.places.model.Attraction;

@Mapper
public interface AttractionMapper {
    AttractionMapper INSTANCE = Mappers.getMapper(AttractionMapper.class);

    Attraction attractionNewRequestToAttraction(AttractionNewRequest attractionNewRequest);

    AttractionFullResponse attractionToAttractionFullResponse(Attraction attraction);

    AttractionShortResponse attractionToAttractionShortResponse(Attraction attraction);
}
