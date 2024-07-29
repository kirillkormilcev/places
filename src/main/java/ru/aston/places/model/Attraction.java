package ru.aston.places.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

/**
 * Attraction entity
 * Relation:
 * MtO: Attraction <-> Location
 * Mtm: Attraction <-> Service
 */
@Getter
@Setter
@Entity
@Table(name = "attraction")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
@ToString
public class Attraction {
    @Id
    @SequenceGenerator(name = "attraction_seq", allocationSize = 1)
    @GeneratedValue(generator = "attraction_seq", strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(length = 64, nullable = false)
    String name;

    @Column(nullable = false)
    LocalDate created;

    @Column(length = 1024)
    String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    AttractionType attractionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    @EqualsAndHashCode.Exclude
    Location location;

    @ManyToMany(mappedBy = "attractions", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @EqualsAndHashCode.Exclude
    List<Service> services;
}
