package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.Categorie;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Categorie, Long> {
    Optional<Categorie> findByCategoryId(Long categoryId);
    Optional<Categorie> findByName(String name);
}
