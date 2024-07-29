package ru.aston.places.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.places.model.Attraction;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
}
