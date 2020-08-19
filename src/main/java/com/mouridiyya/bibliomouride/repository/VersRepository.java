package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.Vers;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VersRepository extends CrudRepository<Vers, Long> {
    Optional<Vers> findByVersId(Long versId);
    Optional<Vers> findByTexteVersAR1AndTexteVersAR2(String texteVersAR1, String texteVersAR2);
}
