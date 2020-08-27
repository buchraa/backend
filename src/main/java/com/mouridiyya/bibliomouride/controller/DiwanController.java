package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Diwan;
import com.mouridiyya.bibliomouride.model.DiwanQuery;
import com.mouridiyya.bibliomouride.service.DiwanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
public class DiwanController {

    @Autowired
    private DiwanService diwanService;

    @GetMapping("/Diwans")
    @PreAuthorize("hasRole('USER') or hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public List<Diwan> getDiwans() {
        return diwanService.getDiwans();
    }

    @PostMapping("/addOrUpdateDiwan")
    @PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public Diwan addOrUpdateTheme(@RequestBody DiwanQuery diwanQuery) {
        return diwanService.addUpdateDiwan(diwanQuery);
    }

    @GetMapping("/Diwan/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public ResponseEntity<Diwan> getDiwan(@PathVariable long id) {
        try {
            Diwan diwan = diwanService.get(id);
            return new ResponseEntity<>(diwan, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Diwan/{id}")
    @PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        diwanService.delete(id);
    }

}