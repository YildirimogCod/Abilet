package org.yildirimog.abilet.event.entity;

import jakarta.persistence.*;
import lombok.*;
import org.yildirimog.abilet.category.entity.Category;
import org.yildirimog.abilet.common.entity.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private String eventRules;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer availableSeat;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;




}
