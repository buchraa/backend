package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Author;
import com.mouridiyya.bibliomouride.entity.Categorie;
import com.mouridiyya.bibliomouride.model.CategoryQuery;
import com.mouridiyya.bibliomouride.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CategorieController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Categorie> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping("/addOrUpdateCategory")
    public Categorie addUpdateCategory(@RequestBody CategoryQuery categoryQuery) {
        return categoryService.addUpdateCategory(categoryQuery);
    }

    @GetMapping("/categorie/{id}")
    public ResponseEntity<Categorie> getCategorie(@PathVariable long id) {
        try {
            Categorie categorie = categoryService.get(id);
            return new ResponseEntity<Categorie>(categorie, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<Categorie>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/categorie/{id}")
    public void delete(@PathVariable long id) {
        categoryService.delete(id);
    }

}