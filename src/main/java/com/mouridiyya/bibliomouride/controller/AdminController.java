package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/populateDBTest")
    public void populateDB() {
         adminService.populateDBTest();
    }

}