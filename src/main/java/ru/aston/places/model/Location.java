package ru.aston.places.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Location entity
 * Relation:
 * OtM: Location <-> Attraction
 */
@Getter
@Setter
@Entity
@Table(name = "location")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(length = 64, nullable = false)
    String name;

    @Column(nullable = false)
    Long population;

    @Column(nullable = false)
    Boolean hasMetro;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    List<Attraction> attractions;
}
