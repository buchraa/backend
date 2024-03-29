package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Author;
import com.mouridiyya.bibliomouride.model.AuthorQuery;
import com.mouridiyya.bibliomouride.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
public class AuteurController {


    @Autowired
    private AuthorService authorService;



    @GetMapping("/Authors")
    @PreAuthorize("hasRole('USER') or hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @PostMapping("/addOrUpdateAuthor")
    @PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public Author addUpdateAuthor(@RequestBody AuthorQuery authorQuery) {
        return authorService.addUpdateAuthor(authorQuery);
    }

    @GetMapping("/Author/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable long id) {
        try {
            Author Author = authorService.get(id);
            return new ResponseEntity<>(Author, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Author/{id}")
    @PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        authorService.delete(id);
    }


}