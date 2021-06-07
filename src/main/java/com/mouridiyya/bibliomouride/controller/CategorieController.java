package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Categorie;
import com.mouridiyya.bibliomouride.entity.CategorieTraduction;
import com.mouridiyya.bibliomouride.entity.Module;
import com.mouridiyya.bibliomouride.model.CategoryQuery;
import com.mouridiyya.bibliomouride.model.CategoryTraductionQuery;
import com.mouridiyya.bibliomouride.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
public class CategorieController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/Categories")
    public List<Categorie> getCategories() {
        return categoryService.getCategories();
    }
    
    @GetMapping("/Module/Categories/{ModuleId}")
    public List<Categorie> getCategoriesForModule(@PathVariable long id) {
        return categoryService.getCategoriesForModule(id);
    }

    @PostMapping("/addOrUpdateCategory")
    @PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public Categorie addUpdateCategory(@RequestBody CategoryQuery categoryQuery) {
        return categoryService.addUpdateCategory(categoryQuery);
    }

    @PostMapping("/addUpdateCategoryTraduction")
    @PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public CategorieTraduction addUpdateCategoryTraduction(@RequestBody CategoryTraductionQuery categoryTraductionQueryQuery) {
        return categoryService.addUpdateCategoryTraduction(categoryTraductionQueryQuery);
    }

    @GetMapping("/Categorie/{id}")
    public ResponseEntity<Categorie> getCategorie(@PathVariable long id) {
        try {
            Categorie categorie = categoryService.get(id);
            return new ResponseEntity<>(categorie, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/Categorie/ByName/{name}")
    public ResponseEntity<Categorie> getCategoryByName(@PathVariable String name) {
        try {
            Categorie category = categoryService.findByName(name);
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/Categorie/{id}")
    @PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        categoryService.delete(id);
    }

}
