package ru.aston.places.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.places.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
