package com.mouridiyya.bibliomouride.service;


import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Author;
import com.mouridiyya.bibliomouride.entity.Oeuvre;
import com.mouridiyya.bibliomouride.model.OeuvreQuery;
import com.mouridiyya.bibliomouride.repository.OeuvreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OeuvreService {

    @Autowired
    private OeuvreRepository OeuvreRepository;


    @Cacheable(cacheNames="findAllOeuvres")
    public List<Oeuvre> getOeuvres() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(OeuvreRepository.findAll());
    }

    @Caching(evict = {
            @CacheEvict(value = "findAllOeuvres", allEntries = true),
            @CacheEvict(value = "findOeuvreById", allEntries = true)})
    public Oeuvre addUpdateOeuvre(OeuvreQuery q) {
        Oeuvre toSave =  new Oeuvre( q.getRefOeuvre(),  q.getTitreOeuvre(),  q.getTitrePopulaire(), q.getDisponibiliteOeuvre(),
                q.getTradFR(), q.getTradEN(),  q.getTradWL(), q.getPdfOeuvre(), q.getPremierVers(), q.getPresentation(),
                q.getDiwanPage(), q.getGenre(), q.getNbVers(), q.getAcrostiche(), q.getMetriqueNom(), q.getRime(),
                q.getPeriode(), q.getPeriodeDatation(), q.getPeriodeLieu(), q.getPeriodeRques(), q.getAuthenticiteDegre(),
                q.getForme_rques(), q.getAvantages(), q.getModesLecture(), q.getEdition(), q.getUrlOeuvre(), q.getAchatOnline(), q.getRemarques());
        if(Optional.ofNullable(q.getRefOeuvre()).orElse(null)!=null && q.getRefOeuvre() !=0){
            Optional<Oeuvre> oldOeuvre = OeuvreRepository.findById(Optional.ofNullable(q.getRefOeuvre()).orElse(null));
            if(oldOeuvre.isPresent()){
                toSave.setRefOeuvre(oldOeuvre.get().getRefOeuvre());
            }
        }
        return OeuvreRepository.save(toSave);
    }

    @Cacheable(cacheNames = "findOeuvreById")
    public Oeuvre get(long id) {
        log.info("Connecting to DB...");
        return OeuvreRepository.findById(id).get();
    }

    @Caching(evict = {
            @CacheEvict(value = "findAllOeuvres", allEntries = true),
            @CacheEvict(value = "findOeuvreById", allEntries = true)})
    public void delete(long id) {
        OeuvreRepository.deleteById(id);
    }


}
