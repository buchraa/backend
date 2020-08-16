package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.Chapitre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChapitreRepository extends CrudRepository<Chapitre, Long> {
    Optional<Chapitre> findByChapitreId(Long chapitreId);
    Optional<Chapitre> findByTitleAndOeuvreOeuvreId(String title, Long oeuvreId);
}
