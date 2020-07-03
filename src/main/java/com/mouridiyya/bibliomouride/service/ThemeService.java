package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Theme;
import com.mouridiyya.bibliomouride.model.ThemeQuery;
import com.mouridiyya.bibliomouride.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    public List<Theme> getThemes() {
        return Lists.newArrayList(themeRepository.findAll());
    }

    public Theme addUpdateTheme(ThemeQuery q) {
        Theme toSave =  new Theme( q.getRefTheme(), q.getNomThemeFR(), q.getNomThemeAR(), q.getNomThemeEN(), q.getNomThemeWL());
        if(Optional.ofNullable(q.getRefTheme()).orElse(null)!=null && q.getRefTheme() !=0){
            Optional<Theme> oldTheme = themeRepository.findById(Optional.ofNullable(q.getRefTheme()).orElse(null));
            if(oldTheme.isPresent()){
                toSave.setRefTheme(oldTheme.get().getRefTheme());
            }
        }
        return themeRepository.save(toSave);
    }

}
