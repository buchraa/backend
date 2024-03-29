package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Module;
import com.mouridiyya.bibliomouride.entity.Oeuvre;
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
    public List<Module> getModules() {
        return moduleService.getModules();
    }

    @PostMapping("/addOrUpdateModule")
    @PreAuthorize("hasRole('ADMIN')")
    public Module  addUpdateModule(@RequestBody ModuleQuery moduleQuery) {
        return moduleService.addUpdateModule(moduleQuery);
    }

    @GetMapping("/Module/ByName/{name}")
    public ResponseEntity<Module> getModuleByName(@PathVariable String name) {
        try {
            Module module = moduleService.findByName(name);
            return new ResponseEntity<>(module, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/Module/{id}")
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
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        moduleService.delete(id);
    }

}