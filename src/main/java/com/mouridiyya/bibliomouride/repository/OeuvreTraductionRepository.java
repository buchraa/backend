package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.OeuvreTraduction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OeuvreTraductionRepository extends CrudRepository<OeuvreTraduction, Long> {
   Optional<OeuvreTraduction> findByTitre(String titre);
   Optional<OeuvreTraduction> findByOeuvreTradId(Long oeuvreTradId);
   Optional<OeuvreTraduction> findByTitreAndCodeLangue(String name, String codeLangue);


}
