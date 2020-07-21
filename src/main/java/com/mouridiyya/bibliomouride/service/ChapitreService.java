package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Author;
import com.mouridiyya.bibliomouride.entity.Chapitre;
import com.mouridiyya.bibliomouride.entity.Oeuvre;
import com.mouridiyya.bibliomouride.model.ChapitreQuery;
import com.mouridiyya.bibliomouride.repository.ChapitreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
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

    @Cacheable(cacheNames="findAllChapters")
    public List<Chapitre> getChapiters() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(chapitreRepository.findAll());
    }


    @Caching(evict = {
            @CacheEvict(value = "findAllChapters", allEntries = true),
            @CacheEvict(value = "findChapterById", allEntries = true)})
    public Chapitre addUpdateChapter(ChapitreQuery q) {
        Chapitre toSave =  new Chapitre( q.getChapterId(),  q.getNum(),  q.getChapterType(), q.getChapterSection(), q.getTheme(), q.getVers(), q.getDispo());
        if(Optional.ofNullable(q.getChapterId()).orElse(null)!=null && q.getChapterId() !=0){
            Optional<Chapitre> oldChapitre = chapitreRepository.findById(Optional.ofNullable(q.getChapterId()).orElse(null));
            if(oldChapitre.isPresent()){
                toSave.setChapterId(oldChapitre.get().getChapterId());
            }
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
}
