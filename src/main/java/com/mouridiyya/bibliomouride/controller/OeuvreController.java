package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Oeuvre;
import com.mouridiyya.bibliomouride.model.OeuvreQuery;
import com.mouridiyya.bibliomouride.service.OeuvreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class OeuvreController {


    @Autowired
    private OeuvreService oeuvreService;

    @GetMapping("/oeuvres")
    public List<Oeuvre> getOeuvres() {
        return oeuvreService.getOeuvres();
    }

    @PostMapping("/addOrUpdateOeuvre")
    public Oeuvre addOrUpdateOeuvre(@RequestBody OeuvreQuery oeuvreQuery) {
        return oeuvreService.addOrUpdateOeuvre(oeuvreQuery);
    }
}