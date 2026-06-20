package org.yildirimog.abilet.category.entity;

import jakarta.persistence.*;
import lombok.*;
import org.yildirimog.abilet.common.entity.BaseEntity;


@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;

    private String description;




}
