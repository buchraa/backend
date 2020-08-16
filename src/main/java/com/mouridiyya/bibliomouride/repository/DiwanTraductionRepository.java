package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.DiwanTraduction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DiwanTraductionRepository extends CrudRepository<DiwanTraduction, Long> {
    Optional<DiwanTraduction> findByNameAndCodeLangue(String name, String codeLangue);
}
