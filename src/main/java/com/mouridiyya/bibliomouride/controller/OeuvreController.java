package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Categorie;
import com.mouridiyya.bibliomouride.entity.Oeuvre;
import com.mouridiyya.bibliomouride.model.OeuvreQuery;
import com.mouridiyya.bibliomouride.service.OeuvreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
public class OeuvreController {


    @Autowired
    private OeuvreService oeuvreService;

    @GetMapping("/oeuvres")
    public List<Oeuvre> getOeuvres() {
        return oeuvreService.getOeuvres();
    }
    
    @GetMapping("/OeuvresForCategory/{categoryId}")
    public List<Oeuvre> getOeuvresForCategory(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
            								  @PathVariable long categoryId) {
        return oeuvreService.getOeuvresForCategory(pageNo, pageSize, categoryId);
    }

    @PostMapping("/addOrUpdateOeuvre")
    @PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public Oeuvre addOrUpdateOeuvre(@RequestBody OeuvreQuery oeuvreQuery) {
        return oeuvreService.addOrUpdateOeuvre(oeuvreQuery);
    }
    @GetMapping("/Oeuvre/ByTitre/{titre}")
    public ResponseEntity<Oeuvre> getOeuvreByTitre(@PathVariable String titre) {
        try {
            Oeuvre oeuvre = oeuvreService.findByTitreOeuvre(titre);
            return new ResponseEntity<>(oeuvre, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/Oeuvre/{id}")
    public ResponseEntity<Oeuvre> getOeuvre(@PathVariable long id) {
        try {
            Oeuvre oeuvre = oeuvreService.get(id);
            return new ResponseEntity<>(oeuvre, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}