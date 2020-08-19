package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Chapitre;
import com.mouridiyya.bibliomouride.entity.ChapitreTraduction;
import com.mouridiyya.bibliomouride.entity.Oeuvre;
import com.mouridiyya.bibliomouride.model.ChapitreQuery;
import com.mouridiyya.bibliomouride.model.ChapitreTraductionQuery;
import com.mouridiyya.bibliomouride.repository.ChapitreRepository;
import com.mouridiyya.bibliomouride.repository.ChapitreTraductionRepository;
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
public class ChapitreService {


    @Autowired
    private ChapitreRepository chapitreRepository;

    @Autowired
    private ChapitreTraductionRepository chapitreTraductionRepository;

    @Autowired
    private OeuvreRepository oeuvreRepository;

    @Cacheable(cacheNames="findAllChapters")
    public List<Chapitre> getChapiters() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(chapitreRepository.findAll());
    }


    @Caching(evict = {
            @CacheEvict(value = "findAllChapters", allEntries = true),
            @CacheEvict(value = "findChapterById", allEntries = true)})
    public Chapitre addUpdateChapitre(ChapitreQuery q) {
        Chapitre toSave =  new Chapitre( q.getChapterId(),  q.getNum(),  q.getChapterType(), q.getChapterSection(), q.getTitle(),  q.getTheme(), q.getPlageVers(),  q.getIsAvailable());
        if(q.getOeuvreId()!=null && q.getOeuvreId()!=0){
            Optional<Oeuvre> oldOeuvre = oeuvreRepository.findById(Optional.ofNullable(q.getOeuvreId()).orElse(null));
            oldOeuvre.ifPresent(toSave::setOeuvre);
        }

        if(Optional.ofNullable(q.getChapterId()).orElse(null)!=null && q.getChapterId() !=0){
            Optional<Chapitre> oldChapitre = chapitreRepository.findById(Optional.ofNullable(q.getChapterId()).orElse(null));
            oldChapitre.ifPresent(chapitre -> toSave.setChapitreId(oldChapitre.get().getChapitreId()));
        }
        return chapitreRepository.save(toSave);
    }

    @Cacheable(cacheNames = "findChapterById")
    public Chapitre get(long id) {
        log.info("Connecting to DB...");
        return chapitreRepository.findById(id).get();
    }

    @Caching(evict = {
            @CacheEvict(value = "findAllChapters", allEntries = true),
            @CacheEvict(value = "findChapterById", allEntries = true)})
    public void delete(long id) {
        chapitreRepository.deleteById(id);
    }

    public Chapitre findByTitleAndOeuvreId(String title, Long oeuvreId) {
        Optional<Chapitre> oldChapitre = chapitreRepository.findByTitleAndOeuvreOeuvreId(title, oeuvreId);
        return oldChapitre.orElse(null);
    }


    public ChapitreTraduction addUpdateChapitreTraduction(ChapitreTraductionQuery q) {
        ChapitreTraduction toSave =  new ChapitreTraduction( q.getChapitreTradId(), q.getName(), q.getCodeLangue());

        if(q.getChapitreId()!=null && q.getChapitreId()!=0){
            Optional<Chapitre> oldChapitre= chapitreRepository.findByChapitreId(q.getChapitreId());
            oldChapitre.ifPresent(toSave::setChapitre);
        }else {
            log.info("chapitreId is not filled...");
            return null;
        }

        if( q.getName()!=null && q.getCodeLangue()!=null){
            Optional<ChapitreTraduction> oldChapitreTraduction = chapitreTraductionRepository.findByNameAndCodeLangue(q.getName(), q.getCodeLangue());
            oldChapitreTraduction.ifPresent(chapitreTraduction -> toSave.setChapitreTradId(chapitreTraduction.getChapitreTradId()));
        }

        return chapitreTraductionRepository.save(toSave);

    }
}
