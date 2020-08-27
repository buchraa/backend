package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Module;
import com.mouridiyya.bibliomouride.model.ModuleQuery;
import com.mouridiyya.bibliomouride.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/Modules")
    @PreAuthorize("hasRole('USER') or hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public List<Module> getModules() {
        return moduleService.getModules();
    }

    @PostMapping("/addOrUpdateModule")
    @PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public Module  addUpdateModule(@RequestBody ModuleQuery moduleQuery) {
        return moduleService.addUpdateModule(moduleQuery);
    }

    @GetMapping("/Module/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public ResponseEntity<Module> getModule(@PathVariable long id) {
        try {
            Module module = moduleService.get(id);
            return new ResponseEntity<>(module, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Module/{id}")
    @PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        moduleService.delete(id);
    }

}