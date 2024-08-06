package ru.aston.places.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.aston.places.controller.dto.LocationNewRequest;
import ru.aston.places.controller.dto.LocationFullResponse;
import ru.aston.places.controller.dto.LocationUpdateRequest;
import ru.aston.places.controller.mapper.LocationMapper;
import ru.aston.places.error.exception.NotFoundException;
import ru.aston.places.model.Location;
import ru.aston.places.repository.LocationRepository;
import ru.aston.places.service.LocationService;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    final LocationRepository locationRepository;
    static final LocationMapper locationMapper = LocationMapper.INSTANCE;

    @Override
    public LocationFullResponse create(LocationNewRequest dto) {
        return locationMapper.locationToLocationFullResponse(
                locationRepository.save(
                    locationMapper.locationNewRequestToLocation(dto)));
    }

    /**
     * for future realization
     * */
    @Override
    public LocationFullResponse findById(Long id) {
        return null;
    }

    /**
     * for future realization
     * */
    @Override
    public List<LocationFullResponse> findAll() {
        return List.of();
    }

    @Override
    public LocationFullResponse update(Long id, LocationUpdateRequest dto) {
        Location locationFromRepository = locationRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Location with id: " + id + " not found"));
        locationFromRepository.setPopulation(dto.getPopulation());
        locationFromRepository.setHasMetro(dto.getHasMetro());
        return locationMapper.locationToLocationFullResponse(
            locationRepository.save(locationFromRepository));
    }

    @Override
    public void delete(Long id) {
        // for future realisation
    }
}
