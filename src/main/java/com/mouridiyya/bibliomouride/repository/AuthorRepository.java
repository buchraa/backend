package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.Auteur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Auteur, Long> {
    Optional<Auteur> findByName(String name);
    Optional<Auteur> findByAuthorId(String name);
}
