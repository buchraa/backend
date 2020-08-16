package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.Diwan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DiwanRepository extends CrudRepository<Diwan, Long> {
    Optional<Diwan> findByDiwanId(Long dinwanId);
    Optional<Diwan> findByName(String name);

}
