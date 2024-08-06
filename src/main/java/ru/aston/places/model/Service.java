package ru.aston.places.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Service entity
 * Relation:
 * MtM: Service <-> Attraction
 */
@Getter
@Setter
@Entity
@Table(name = "service")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
@ToString
@Builder
public class Service {
    @Id
    @SequenceGenerator(name = "service_seq", allocationSize = 1)
    @GeneratedValue(generator = "service_seq", strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(length = 64, nullable = false)
    String name;

    @Column(length = 1024)
    String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "services_attractions",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "attraction_id")
    )
    @EqualsAndHashCode.Exclude
    List<Attraction> attractions;
}
