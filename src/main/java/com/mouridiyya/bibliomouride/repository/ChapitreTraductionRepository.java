package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.ChapitreTraduction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChapitreTraductionRepository extends CrudRepository<ChapitreTraduction, Long> {
    Optional<ChapitreTraduction> findByNameAndCodeLangue(String name, String codeLangue);
}
