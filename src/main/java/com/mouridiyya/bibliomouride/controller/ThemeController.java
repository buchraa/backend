package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Theme;
import com.mouridiyya.bibliomouride.model.ThemeQuery;
import com.mouridiyya.bibliomouride.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping("/themes")
    public List<Theme> getThemes() {
        return themeService.getThemes();
    }

    @PostMapping("/addOrUpdateTheme")
    public Theme addOrUpdateTheme(@RequestBody ThemeQuery themeQuery) {
        return themeService.addUpdateTheme(themeQuery);
    }

}