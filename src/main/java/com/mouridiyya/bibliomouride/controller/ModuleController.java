package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Diwan;
import com.mouridiyya.bibliomouride.entity.Module;
import com.mouridiyya.bibliomouride.model.ModuleQuery;
import com.mouridiyya.bibliomouride.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/modules")
    public List<Module> getModules() {
        return moduleService.getModules();
    }

    @PostMapping("/addOrUpdateModule")
    public Module  addUpdateModule(@RequestBody ModuleQuery moduleQuery) {
        return moduleService.addUpdateModule(moduleQuery);
    }

    @GetMapping("/module/{id}")
    public ResponseEntity<Module> getModule(@PathVariable long id) {
        try {
            Module module = moduleService.get(id);
            return new ResponseEntity<Module>(module, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<Module>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/module/{id}")
    public void delete(@PathVariable long id) {
        moduleService.delete(id);
    }

}