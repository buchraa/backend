package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.*;
import com.mouridiyya.bibliomouride.model.OeuvreQuery;
import com.mouridiyya.bibliomouride.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class OeuvreService {

    @Autowired
    private OeuvreRepository oeuvreRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private DiwanRepository diwanRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Oeuvre> getOeuvres() {
        return Lists.newArrayList(oeuvreRepository.findAll());
    }

    public Oeuvre addOrUpdateOeuvre(OeuvreQuery oeuvreQuery) {
        Categorie categery = null;
        Theme theme = null;
        Diwan diwan = null;
        Long refCateg = Optional.ofNullable(oeuvreQuery.getRef_categ()).orElse(0L);
        if(refCateg!=0){
            Optional<Categorie> oCategory = categoryRepository.findByCategoryId(oeuvreQuery.getRef_categ());
            if(oCategory.isPresent()){
                categery =  oCategory.get();
            }else{
                log.warn("category is missing");
            }
        }

        Long diwanOrigine = Optional.ofNullable(oeuvreQuery.getDiwan_origine()).orElse(0L);
        if(diwanOrigine!=0){
            Optional<Diwan> oDiwan = diwanRepository.findByDiwanId(oeuvreQuery.getDiwan_origine());
            if(oDiwan.isPresent()){
                diwan =  oDiwan.get();
            }else{
                log.warn("diwan is missing");
            }
        }

        Long themePrincipal = Optional.ofNullable(oeuvreQuery.getTheme_principal()).orElse(0L);
        if(themePrincipal!=null && themePrincipal!=0){
            Optional<Theme> oTheme = themeRepository.findByThemeId(oeuvreQuery.getTheme_principal());
            if(oTheme.isPresent()){
                theme =  oTheme.get();
            }else{
                log.warn("theme is missing");
            }
        }

        Set<Author> authors = new HashSet<>();
        Set<Long> authorIds = oeuvreQuery.getRefAuthors();
        for(Long authorId : authorIds){
            Optional<Author> oAuthor =  authorRepository.findByAuthorId(authorId);
            if(oAuthor.isPresent()){
                authors.add(oAuthor.get());
            }else{
                log.warn("author is missing");
            }
        }


        Oeuvre oeuvre;
        oeuvre = new Oeuvre (oeuvreQuery.getOeuvreId(), categery,  authors, oeuvreQuery.getTitre_oeuvre(), oeuvreQuery.getTitre_populaire(),
                oeuvreQuery.getTitrear(),  oeuvreQuery.getTitrefr(), oeuvreQuery.getTitreen(),  oeuvreQuery.getTitrewl(),  oeuvreQuery.getDispo_oeuvre(),
                oeuvreQuery.getTradfr(), oeuvreQuery.getTraden(), oeuvreQuery.getTitrewl(), oeuvreQuery.getPdf_oeuvre(), oeuvreQuery.getPremier_vers(), theme, oeuvreQuery.getPresentation(),
                diwan, oeuvreQuery.getDiwan_page(), oeuvreQuery.getGenre(), oeuvreQuery.getNb_vers(), oeuvreQuery.getAcrostiche(), oeuvreQuery.getMetrique_nom(), oeuvreQuery.getRime(),
                oeuvreQuery.getPeriode(), oeuvreQuery.getPeriode_datation(), oeuvreQuery.getPeriode_lieu(), oeuvreQuery.getPeriode_rques(), oeuvreQuery.getAuthenticite_degre(), oeuvreQuery.getForme_rques(),
                oeuvreQuery.getAvantages(), oeuvreQuery.getModes_lecture(), oeuvreQuery.getEdition(), oeuvreQuery.getUrl_oeuvre(), oeuvreQuery.getAchat_online(),  oeuvreQuery.getRemarques());

        if(oeuvreQuery.getOeuvreId()!=null){
            Optional<Oeuvre> oldOeuvre = oeuvreRepository.findById(oeuvreQuery.getOeuvreId());
            oldOeuvre.ifPresent(value -> oeuvre.setOeuvreId(value.getOeuvreId()));
        }

        Oeuvre savedOeuvre = oeuvreRepository.save(oeuvre);

        log.warn("successfully saved");
        return savedOeuvre;
    }

    public Oeuvre findByTitreOeuvre(String titreOeuvre) {
        Optional<Oeuvre> oldOeuvre = oeuvreRepository.findByTitreOeuvre(titreOeuvre);
        return oldOeuvre.orElse(null);
    }
}
