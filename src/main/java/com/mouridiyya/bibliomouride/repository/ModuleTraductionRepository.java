package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.ModuleTraduction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ModuleTraductionRepository extends CrudRepository<ModuleTraduction, Long> {
    Optional<ModuleTraduction> findByNameAndCodeLangue(String name, String codeLangue);
}
