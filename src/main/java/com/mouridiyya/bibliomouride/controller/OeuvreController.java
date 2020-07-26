package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Oeuvre;
import com.mouridiyya.bibliomouride.entity.Oeuvre;
import com.mouridiyya.bibliomouride.model.OeuvreQuery;
import com.mouridiyya.bibliomouride.model.OeuvreQuery;
import com.mouridiyya.bibliomouride.service.OeuvreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class OeuvreController {
    @Autowired
    private OeuvreService oeuvreService;

    //@RolesAllowed("user")
    @GetMapping("/Oeuvres")
    public List<Oeuvre> getOeuvres() {
        return oeuvreService.getOeuvres();
    }

    @PostMapping("/addOrUpdateOeuvre")
    public Oeuvre addUpdateOeuvre(@RequestBody OeuvreQuery oeuvreQuery) {
        return oeuvreService.addUpdateOeuvre(oeuvreQuery);
    }

    @GetMapping("/Oeuvre/{id}")
    public ResponseEntity<Oeuvre> getOeuvre(@PathVariable long id) {
        try {
            Oeuvre oeuvre = oeuvreService.get(id);
            return new ResponseEntity<Oeuvre>(oeuvre, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<Oeuvre>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Oeuvre/{id}")
    public void delete(@PathVariable long id) {
        oeuvreService.delete(id);
    }

}
