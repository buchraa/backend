package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.ThemeTraduction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ThemeTraductionRepository extends CrudRepository<ThemeTraduction, Long> {
    Optional<ThemeTraduction> findByNameAndCodeLangue(String name, String codeLangue);
}
