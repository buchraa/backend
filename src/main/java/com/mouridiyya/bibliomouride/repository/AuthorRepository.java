package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findByName(String name);
    Optional<Author> findByAuthorId(Long name);
}
