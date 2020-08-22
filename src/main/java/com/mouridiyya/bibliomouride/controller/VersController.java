package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Vers;
import com.mouridiyya.bibliomouride.model.VersQuery;
import com.mouridiyya.bibliomouride.service.VersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
public class VersController {


    @Autowired
    private VersService versService;



    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/vers")
    public List<Vers> getVers() {
        return versService.getVers();
    }

    @PostMapping("/addOrUpdateVers")
    public Vers addOrUpdateVers(@RequestBody VersQuery versQuery) {
        return versService.addUpdateVers(versQuery);
    }

    @GetMapping("/Vers/{id}")
    public ResponseEntity<Vers> getAuthor(@PathVariable long id) {
        try {
            Vers vers = versService.get(id);
            return new ResponseEntity<>(vers, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Vers/{id}")
    public void delete(@PathVariable long id) {
        versService.delete(id);
    }


}