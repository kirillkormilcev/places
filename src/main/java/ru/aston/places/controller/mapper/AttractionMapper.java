package ru.aston.places.controller.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.aston.places.controller.dto.AttractionFullResponse;
import ru.aston.places.controller.dto.AttractionNewRequest;
import ru.aston.places.controller.dto.AttractionShortResponse;
import ru.aston.places.controller.dto.LocationShortResponse;
import ru.aston.places.model.Attraction;
import ru.aston.places.model.Location;
import ru.aston.places.repository.LocationRepository;

@Mapper
public interface AttractionMapper {
    AttractionMapper INSTANCE = Mappers.getMapper(AttractionMapper.class);

    @Mapping(source = "locationId", target = "location")
    Attraction attractionNewRequestToAttraction(AttractionNewRequest attractionNewRequest, @Context LocationRepository locationRepository);

    @Mapping(target = "location", expression = "java(mapLocationToLocationShortResponse(attraction.getLocation()))")
    AttractionFullResponse attractionToAttractionFullResponse(Attraction attraction);

    AttractionShortResponse attractionToAttractionShortResponse(Attraction attraction);

    default Location mapLocationIdToLocation(Long locationId, @Context LocationRepository locationRepository) {
        return locationRepository.findById(locationId).orElse(null);
    }

    default LocationShortResponse mapLocationToLocationShortResponse(Location location) {
        return LocationMapper.INSTANCE.locationToLocationShortResponse(location);
    }
}
