package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Diwan;
import com.mouridiyya.bibliomouride.entity.Theme;
import com.mouridiyya.bibliomouride.model.ThemeQuery;
import com.mouridiyya.bibliomouride.repository.ThemeRepository;
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
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Cacheable(cacheNames="findAllTheme")
    public List<Theme> getThemes() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(themeRepository.findAll());
    }


    @Caching(evict = {
            @CacheEvict(value = "findAllTheme", allEntries = true),
            @CacheEvict(value = "findThemeById", allEntries = true)})
    public Theme addUpdateTheme(ThemeQuery q) {
        Theme toSave =  new Theme( q.getRefTheme());
        if(Optional.ofNullable(q.getRefTheme()).orElse(null)!=null && q.getRefTheme() !=0){
            Optional<Theme> oldTheme = themeRepository.findById(Optional.ofNullable(q.getRefTheme()).orElse(null));
            if(oldTheme.isPresent()){
                toSave.setRefTheme(oldTheme.get().getRefTheme());
            }
        }
        return themeRepository.save(toSave);
    }

    @Cacheable(cacheNames="findThemeById")
    public Theme get(long id) {
        log.info("Connecting to DB...");
        return themeRepository.findById(id).get();
    }

    public void delete(long id) {
        themeRepository.deleteById(id);
    }


}
