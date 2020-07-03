package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Diwan;
import com.mouridiyya.bibliomouride.model.DiwanQuery;
import com.mouridiyya.bibliomouride.service.DiwanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}