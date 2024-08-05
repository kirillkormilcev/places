package ru.aston.places.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.aston.places.controller.dto.AttractionFullResponse;
import ru.aston.places.controller.dto.AttractionNewRequest;
import ru.aston.places.controller.dto.AttractionShortResponse;
import ru.aston.places.controller.dto.AttractionUpdateRequest;
import ru.aston.places.controller.mapper.AttractionMapper;
import ru.aston.places.model.Attraction;
import ru.aston.places.model.AttractionParameters;
import ru.aston.places.repository.AttractionRepository;
import ru.aston.places.repository.LocationRepository;
import ru.aston.places.service.AttractionService;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {
    final AttractionRepository attractionRepository;
    final LocationRepository locationRepository;
    static final AttractionMapper attractionMapper = AttractionMapper.INSTANCE;

    @Override
    public AttractionFullResponse create(AttractionNewRequest dto) {
        /*Attraction attraction = attractionRepository.save(
            attractionMapper.attractionNewRequestToAttraction(dto, locationRepository));*/
        return attractionMapper.attractionToAttractionFullResponse(attractionRepository.save(
            attractionMapper.attractionNewRequestToAttraction(dto, locationRepository)));
    }

    @Override
    public AttractionFullResponse findById(Long id) {
        return null;
    }

    @Override
    public List<AttractionFullResponse> findAll() {
        return List.of();
    }

    @Override
    public AttractionFullResponse update(Long id, AttractionUpdateRequest dto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        // TODO document why this method is empty
    }

    @Override
    public List<AttractionShortResponse> findAttractionsWith(AttractionParameters parameters) {
        return List.of();
    }
}
