package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Categorie;
import com.mouridiyya.bibliomouride.model.CategoryQuery;
import com.mouridiyya.bibliomouride.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}