package org.yildirimog.abilet.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yildirimog.abilet.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
}
