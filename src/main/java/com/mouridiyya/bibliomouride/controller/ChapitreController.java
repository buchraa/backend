package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Author;
import com.mouridiyya.bibliomouride.entity.Chapitre;
import com.mouridiyya.bibliomouride.model.AuthorQuery;
import com.mouridiyya.bibliomouride.model.ChapitreQuery;
import com.mouridiyya.bibliomouride.service.ChapitreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ChapitreController {


    @Autowired
    private ChapitreService chapitreService;

    @GetMapping("/Chapters")
    public List<Chapitre> getChapters() {
        return chapitreService.getChapiters();
    }

    @PostMapping("/addOrUpdateChapter")
    public Chapitre addUpdateChapter(@RequestBody ChapitreQuery chapitreQuery) {
        return chapitreService.addUpdateChapter(chapitreQuery);
    }

    @GetMapping("/Chapter/{id}")
    public ResponseEntity<Chapitre> getChapter(@PathVariable long id) {
        try {
            Chapitre chapitre = chapitreService.get(id);
            return new ResponseEntity<Chapitre>(chapitre, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<Chapitre>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Chapter/{id}")
    public void delete(@PathVariable long id) {
        chapitreService.delete(id);
    }
}
