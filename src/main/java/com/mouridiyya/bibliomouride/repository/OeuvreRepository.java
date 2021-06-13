package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.Categorie;
import com.mouridiyya.bibliomouride.entity.Oeuvre;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OeuvreRepository extends PagingAndSortingRepository<Oeuvre, Long> {
   Optional<Oeuvre> findByTitreOeuvre(String titreOeuvre);
   Optional<Oeuvre> findByOeuvreId(Long oeuvreId);
   
   
   @Query("select o from Oeuvre o where o.category.categoryId = ?1")
   Page<Oeuvre> findByCategoryId(Long categoryId, Pageable pageable);
   
   @Query("select o from Oeuvre o where LOWER(o.titre) like %?1% or LOWER(o.titreOeuvre) like %?1% or LOWER(o.titrePopulaire) like %?1%")
  List <Oeuvre> findByTitreOrTitreOeuvre(String titre);
   
   

}
