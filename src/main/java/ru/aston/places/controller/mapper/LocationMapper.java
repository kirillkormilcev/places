package ru.aston.places.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.aston.places.controller.dto.LocationNewRequest;
import ru.aston.places.controller.dto.LocationFullResponse;
import ru.aston.places.controller.dto.LocationShortResponse;
import ru.aston.places.model.Location;

@Mapper
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    Location locationNewRequestToLocation(LocationNewRequest locationNewRequest);

    LocationFullResponse locationToLocationFullResponse(Location location);

    LocationShortResponse locationToLocationShortResponse(Location location);
}
