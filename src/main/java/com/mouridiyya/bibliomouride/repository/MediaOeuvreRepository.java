package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.MediaOeuvre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MediaOeuvreRepository extends CrudRepository<MediaOeuvre, Long> {
    Optional<MediaOeuvre> findByMediaOeuvreId(Long mediaOeuvreId);
}
