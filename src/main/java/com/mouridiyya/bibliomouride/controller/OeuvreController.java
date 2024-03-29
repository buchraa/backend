package com.mouridiyya.bibliomouride.controller;


import com.mouridiyya.bibliomouride.entity.Categorie;
import com.mouridiyya.bibliomouride.entity.Oeuvre;
import com.mouridiyya.bibliomouride.entity.OeuvreGeneralView;
import com.mouridiyya.bibliomouride.model.OeuvreQuery;
import com.mouridiyya.bibliomouride.service.OeuvreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
public class OeuvreController {


    @Autowired
    private OeuvreService oeuvreService;

    @GetMapping("/oeuvres")
    public Page<Oeuvre> getOeuvres(
            @RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "8") Integer pageSize,
            @RequestParam(defaultValue = "OeuvreId") String sortBy) 
    {   
    		 return  oeuvreService.getOeuvres(pageNo, pageSize, sortBy);  
    }

    @GetMapping("/manageoeuvres")
    public List<Oeuvre> getAllOeuvres()
    {   
    		 return  oeuvreService.manageOeuvres();  
    }
    
    
    @GetMapping("/OeuvresForCategory/{categoryId}")
    public Page<Oeuvre> getOeuvresForCategory(@RequestParam(defaultValue = "0") Integer pageNo,
    										  @RequestParam(defaultValue = "8") Integer pageSize,
            								  @PathVariable long categoryId) 
    {
    	
    		return oeuvreService.getOeuvresForCategory(categoryId, pageNo, pageSize);
    	
    }

    @PostMapping("/addOrUpdateOeuvre")
    @PreAuthorize("hasRole('TRANSLATOR') or hasRole('ADMIN')")
    public Oeuvre addOrUpdateOeuvre(@RequestBody OeuvreQuery oeuvreQuery) {
        return oeuvreService.addOrUpdateOeuvre(oeuvreQuery);
    }
    @GetMapping("/Oeuvre/ByTitre/{titre}")
    public ResponseEntity<Oeuvre> getOeuvreByTitre(@PathVariable String titre) {
        try {
            Oeuvre oeuvre = oeuvreService.findByTitreOeuvre(titre);
            return new ResponseEntity<>(oeuvre, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/Oeuvre/{id}")
    public ResponseEntity<Oeuvre> getOeuvre(@PathVariable long id) {
        try {
            Oeuvre oeuvre = oeuvreService.get(id);
            return new ResponseEntity<>(oeuvre, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/oeuvres/query")
    public List<Oeuvre> queryOeuvres(
            @RequestParam String titre 
           ) 
    {   
    		 return  oeuvreService.queryOeuvres(titre);  
    }

    @GetMapping("/generalsearch")
    public Page<OeuvreGeneralView> generalsearch(
            @RequestParam String searchText,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "8") Integer pageSize,
            @RequestParam(defaultValue = "rownum") String sortBy)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return  oeuvreService.generalsearch(searchText, pageable);
    }

}
