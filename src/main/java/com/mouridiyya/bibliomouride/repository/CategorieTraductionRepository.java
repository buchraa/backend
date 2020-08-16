package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.CategorieTraduction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategorieTraductionRepository extends CrudRepository<CategorieTraduction, Long> {
    Optional<CategorieTraduction> findByNameAndCodeLangue(String name, String codeLangue);
}
