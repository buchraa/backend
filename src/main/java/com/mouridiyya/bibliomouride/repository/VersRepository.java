package com.mouridiyya.bibliomouride.repository;



import com.mouridiyya.bibliomouride.entity.Vers;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD
=======
import org.springframework.data.repository.CrudRepository;
>>>>>>> b9e201b596f20b5589149cf4bf2806a25756fd0b
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface VersRepository extends PagingAndSortingRepository<Vers, Long> {
    Optional<Vers> findByVersId(Long versId);
    Optional<Vers> findByTexteVersAR1AndTexteVersAR2(String texteVersAR1, String texteVersAR2);
    
    
    @Query("select v from Vers v where v.oeuvre.oeuvreId = ?1")
    List<Vers> findByOeuvreId(Long oeuvreId, Sort sort);
}
