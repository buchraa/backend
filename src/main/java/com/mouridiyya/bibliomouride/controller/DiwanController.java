package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Categorie;
import com.mouridiyya.bibliomouride.entity.Diwan;
import com.mouridiyya.bibliomouride.model.DiwanQuery;
import com.mouridiyya.bibliomouride.service.DiwanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class DiwanController {

    @Autowired
    private DiwanService diwanService;

    @GetMapping("/diwans")
    public List<Diwan> getDiwans() {
        return diwanService.getDiwans();
    }

    @PostMapping("/addOrUpdateDiwan")
    public Diwan addOrUpdateTheme(@RequestBody DiwanQuery diwanQuery) {
        return diwanService.addUpdateDiwan(diwanQuery);
    }

    @GetMapping("/diwan/{id}")
    public ResponseEntity<Diwan> getDiwan(@PathVariable long id) {
        try {
            Diwan diwan = diwanService.get(id);
            return new ResponseEntity<Diwan>(diwan, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<Diwan>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/diwan/{id}")
    public void delete(@PathVariable long id) {
        diwanService.delete(id);
    }

}