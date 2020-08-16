package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.Oeuvre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OeuvreRepository extends CrudRepository<Oeuvre, Long> {
   Optional<Oeuvre> findByTitreOeuvre(String titreOeuvre);
}
