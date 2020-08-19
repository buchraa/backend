package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.VersTraduction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VersTraductionRepository extends CrudRepository<VersTraduction, Long> {
    Optional<VersTraduction> findByTexteAndCodeLangue(String name, String codeLangue);
    Optional<VersTraduction> findByVersTradId(Long versTradId);

}
