package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Module;
import com.mouridiyya.bibliomouride.model.ModuleQuery;
import com.mouridiyya.bibliomouride.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}