package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Vers;
import com.mouridiyya.bibliomouride.entity.VersTraduction;
import com.mouridiyya.bibliomouride.model.VersQuery;
import com.mouridiyya.bibliomouride.model.VersTraductionQuery;
import com.mouridiyya.bibliomouride.service.VersService;
import com.mouridiyya.bibliomouride.service.VersTraductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
public class VersTraductionController {

    @Autowired
    private VersTraductionService versTraductionService;

    @GetMapping("/versTraduction")
    public List<VersTraduction> getVers() {
        return versTraductionService.getVersTraduction();
    }

    @PostMapping("/addOrUpdateVersTraduction")
    @PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public VersTraduction addOrUpdateVersTraduction(@RequestBody VersTraductionQuery versTraductionQuery) {
        return versTraductionService.addUpdateVersTraduction(versTraductionQuery);
    }

    @GetMapping("/versTrad/{id}")
    public ResponseEntity<VersTraduction> getVersTrad(@PathVariable long id) {
        try {
            VersTraduction versTraduction = versTraductionService.findById(id);
            return new ResponseEntity<>(versTraduction, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/versTrad/{id}")
    @PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        versTraductionService.delete(id);
    }

}
