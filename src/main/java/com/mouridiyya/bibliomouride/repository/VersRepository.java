package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.Vers;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VersRepository extends CrudRepository<Vers, Long> {
    Optional<Vers> findByRefVers(Long refVers);
}
