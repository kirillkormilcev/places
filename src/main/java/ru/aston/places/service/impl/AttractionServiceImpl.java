package ru.aston.places.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.aston.places.controller.dto.AttractionFullResponse;
import ru.aston.places.controller.dto.AttractionNewRequest;
import ru.aston.places.controller.dto.AttractionShortResponse;
import ru.aston.places.controller.dto.AttractionUpdateRequest;
import ru.aston.places.controller.mapper.AttractionMapper;
import ru.aston.places.error.exception.IncorrectRequestParamException;
import ru.aston.places.error.exception.NotFoundException;
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
    private final EntityManager em;

    @Override
    public AttractionFullResponse create(AttractionNewRequest dto) {
        return attractionMapper.attractionToAttractionFullResponse(attractionRepository.save(
            attractionMapper.attractionNewRequestToAttraction(dto, locationRepository)));
    }

    /**
     * for future realization
     * */
    @Override
    public AttractionFullResponse findById(Long id) {
        return null;
    }

    /**
     * for future realization
     * */
    @Override
    public List<AttractionFullResponse> findAll() {
        return List.of();
    }

    @Override
    public AttractionFullResponse update(Long id, AttractionUpdateRequest dto) {
        Attraction attractionFromRepository = checkAttractionExistence(id);
        attractionFromRepository.setDescription(dto.getDescription());
        return attractionMapper.attractionToAttractionFullResponse(
            attractionRepository.save(attractionFromRepository));
    }

    @Override
    public void delete(Long id) {
        checkAttractionExistence(id);
        attractionRepository.deleteById(id);
    }

    @Override
    public List<AttractionShortResponse> findAttractionsWith(AttractionParameters parameters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Attraction> cq = cb.createQuery(Attraction.class);
        Root<Attraction> root = cq.from(Attraction.class);
        List<Predicate> predicates = new ArrayList<>();
        if (parameters.getSort() != null) {
          if (parameters.getSort().equals("NAME")) {
            cq.orderBy(cb.asc(root.get("name")));
          } else {
            throw new IncorrectRequestParamException(
                "Параметр sort=" + parameters.getSort() + " не верный.");
          }
        }
        if (parameters.getFilter() != null) {
            predicates.add(cb.like(cb.lower(root.get("attractionType")),
                "%" + parameters.getFilter().toLowerCase() + "%"));
        }
        if (parameters.getLocation() != null) {
            predicates.add(cb.equal(root.get("location").get("name"), parameters.getLocation()));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Attraction> tq = em.createQuery(cq);
        List<Attraction> attractions = tq.getResultList();
        return attractions.stream().map(
            AttractionMapper.INSTANCE::attractionToAttractionShortResponse).toList();
    }
    private Attraction checkAttractionExistence(Long id) {
        return attractionRepository.findById(id).orElseThrow(() ->
            new NotFoundException("Attraction with id: " + id + " not found"));
    }
}
