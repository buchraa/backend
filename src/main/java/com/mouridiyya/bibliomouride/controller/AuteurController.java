package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Auteur;
import com.mouridiyya.bibliomouride.model.AuthorQuery;
import com.mouridiyya.bibliomouride.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}