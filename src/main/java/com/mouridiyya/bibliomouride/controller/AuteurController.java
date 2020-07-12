package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Auteur;
import com.mouridiyya.bibliomouride.model.AuthorQuery;
import com.mouridiyya.bibliomouride.service.AuthorService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class AuteurController {


    @Autowired
    private AuthorService authorService;

    @GetMapping("/auteurs")
    public List<Auteur> getAuthors() {
        return authorService.getAuthors();
    }

    @PostMapping("/addOrUpdateAuteur")
    public Auteur addUpdateAuthor(@RequestBody AuthorQuery authorQuery) {
        return authorService.addUpdateAuthor(authorQuery);
    }

    @GetMapping("/auteur/{id}")
    public ResponseEntity<Auteur> getAuthor(@PathVariable long id) {
        try {
            Auteur auteur = authorService.get(id);
            return new ResponseEntity<Auteur>(auteur, HttpStatus.OK);
            }
        catch (NoSuchElementException e)
            {
            return new ResponseEntity<Auteur>(HttpStatus.NOT_FOUND);
            }
    }

    @DeleteMapping("/auteur/{id}")
    public void delete(@PathVariable long id) {
        authorService.delete(id);
    }


}