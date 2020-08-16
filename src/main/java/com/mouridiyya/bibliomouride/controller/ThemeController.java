package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Theme;
import com.mouridiyya.bibliomouride.model.ThemeQuery;
import com.mouridiyya.bibliomouride.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping("/Themes")
    public List<Theme> getThemes() {
        return themeService.getThemes();
    }

    @PostMapping("/addOrUpdateTheme")
    public Theme addOrUpdateTheme(@RequestBody ThemeQuery themeQuery) {
        return themeService.addUpdateTheme(themeQuery);
    }

    @GetMapping("/Theme/{id}")
    public ResponseEntity<Theme> getTheme(@PathVariable long id) {
        try {
            Theme theme = themeService.get(id);
            return new ResponseEntity<>(theme, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Theme/{id}")
    public void delete(@PathVariable long id) {
        themeService.delete(id);
    }

}