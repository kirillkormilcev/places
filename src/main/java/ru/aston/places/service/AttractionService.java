package ru.aston.places.service;

import ru.aston.places.controller.dto.AttractionFullResponse;
import ru.aston.places.controller.dto.AttractionNewRequest;
import ru.aston.places.controller.dto.AttractionShortResponse;
import ru.aston.places.controller.dto.AttractionUpdateRequest;
import ru.aston.places.model.AttractionParameters;

import java.util.List;

public interface AttractionService extends Service<
        AttractionNewRequest,
        AttractionUpdateRequest,
        AttractionFullResponse,
        Long> {

    public List<AttractionShortResponse> findAttractionsWith(AttractionParameters parameters);
}
