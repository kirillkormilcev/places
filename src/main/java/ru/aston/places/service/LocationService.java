package ru.aston.places.service;

import ru.aston.places.controller.dto.LocationFullResponse;
import ru.aston.places.controller.dto.LocationNewRequest;
import ru.aston.places.controller.dto.LocationUpdateRequest;

public interface LocationService extends Service<
        LocationNewRequest,
        LocationUpdateRequest,
        LocationFullResponse,
        Long> {
}
