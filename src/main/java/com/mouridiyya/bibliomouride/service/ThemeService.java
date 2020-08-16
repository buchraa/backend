package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Theme;
import com.mouridiyya.bibliomouride.entity.ThemeTraduction;
import com.mouridiyya.bibliomouride.model.ThemeQuery;
import com.mouridiyya.bibliomouride.model.ThemeTraductionQuery;
import com.mouridiyya.bibliomouride.repository.ThemeRepository;
import com.mouridiyya.bibliomouride.repository.ThemeTraductionRepository;
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

    @Autowired
    private ThemeTraductionRepository themeTraductionRepository;

    @Cacheable(cacheNames="findAllTheme")
    public List<Theme> getThemes() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(themeRepository.findAll());
    }


    @Caching(evict = {
            @CacheEvict(value = "findAllTheme", allEntries = true),
            @CacheEvict(value = "findThemeById", allEntries = true)})
    public Theme addUpdateTheme(ThemeQuery q) {
        Theme toSave =  new Theme( q.getThemeId(), q.getName(), q.getIsAvailable());
        if( q.getName()!=null && !q.getName().isEmpty()){
            Optional<Theme> oldTheme = themeRepository.findByName(q.getName());
            oldTheme.ifPresent(theme -> toSave.setThemeId(theme.getThemeId()));
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

    public Theme findByName(String name) {
        Optional<Theme> oldTheme = themeRepository.findByName(name);
        return oldTheme.orElse(null);
    }

    public ThemeTraduction addUpdateThemeTraduction(ThemeTraductionQuery q) {
        ThemeTraduction toSave =  new ThemeTraduction( q.getThemeTradId(), q.getName(), q.getCodeLangue());

        if(q.getThemeId()!=null && q.getThemeId()!=0){
            Optional<Theme> oldTheme= themeRepository.findByThemeId(q.getThemeId());
            oldTheme.ifPresent(toSave::setTheme);
        }else {
            log.info("themeId is not filled...");
            return null;
        }

        if( q.getName()!=null && q.getCodeLangue()!=null){
            Optional<ThemeTraduction> oldDiwanTraduction = themeTraductionRepository.findByNameAndCodeLangue(q.getName(), q.getCodeLangue());
            oldDiwanTraduction.ifPresent(themeTraduction -> toSave.setThemeTradId(themeTraduction.getThemeTradId()));
        }

        return themeTraductionRepository.save(toSave);

    }



}
