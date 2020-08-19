package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.*;
import com.mouridiyya.bibliomouride.model.CategoryTraductionQuery;
import com.mouridiyya.bibliomouride.model.OeuvreQuery;
import com.mouridiyya.bibliomouride.model.OeuvreTraductionQuery;
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

    @Autowired
    private OeuvreTraductionRepository oeuvreTraductionRepository;

    public List<Oeuvre> getOeuvres() {
        return Lists.newArrayList(oeuvreRepository.findAll());
    }

    public Oeuvre addOrUpdateOeuvre(OeuvreQuery oeuvreQuery) {
        Categorie categery = null;
        Theme theme = null;
        Diwan diwan = null;
        Long refCateg = Optional.ofNullable(oeuvreQuery.getCategoryId()).orElse(0L);
        if(refCateg!=0){
            Optional<Categorie> oCategory = categoryRepository.findByCategoryId(oeuvreQuery.getCategoryId());
            if(oCategory.isPresent()){
                categery =  oCategory.get();
            }else{
                log.warn("category is missing");
            }
        }

        Long diwanOrigine = Optional.ofNullable(oeuvreQuery.getDiwanOrigine()).orElse(0L);
        if(diwanOrigine!=0){
            Optional<Diwan> oDiwan = diwanRepository.findByDiwanId(oeuvreQuery.getDiwanOrigine());
            if(oDiwan.isPresent()){
                diwan =  oDiwan.get();
            }else{
                log.warn("diwan is missing");
            }
        }

        Long themePrincipal = Optional.ofNullable(oeuvreQuery.getThemePrincipal()).orElse(0L);
        if(themePrincipal!=null && themePrincipal!=0){
            Optional<Theme> oTheme = themeRepository.findByThemeId(oeuvreQuery.getThemePrincipal());
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
        oeuvre = new Oeuvre (oeuvreQuery.getOeuvreId(), categery,  authors, oeuvreQuery.getTitreOeuvre(), oeuvreQuery.getTitrePopulaire(),
                oeuvreQuery.getTitre(), oeuvreQuery.getDispo_oeuvre(),
              oeuvreQuery.getPdfOeuvre(), oeuvreQuery.getPremierVers(), theme, oeuvreQuery.getPresentation(),
                diwan, oeuvreQuery.getDiwanPage(), oeuvreQuery.getGenre(), oeuvreQuery.getNbVers(), oeuvreQuery.getAcrostiche(), oeuvreQuery.getMetriqueNom(), oeuvreQuery.getRime(),
                oeuvreQuery.getPeriode(), oeuvreQuery.getPeriodeDatation(), oeuvreQuery.getPeriodeLieu(), oeuvreQuery.getPeriodeRques(), oeuvreQuery.getAuthenticite_degre(), oeuvreQuery.getFormeRques(),
                oeuvreQuery.getAvantages(), oeuvreQuery.getModesLecture(), oeuvreQuery.getEdition(), oeuvreQuery.getUrlOeuvre(), oeuvreQuery.getAchatOnline(),  oeuvreQuery.getRemarques());

        if(oeuvreQuery.getOeuvreId()!=null){
            Optional<Oeuvre> oldOeuvre = oeuvreRepository.findById(oeuvreQuery.getOeuvreId());
            oldOeuvre.ifPresent(value -> oeuvre.setOeuvreId(value.getOeuvreId()));
        }

        if(oeuvreQuery.getOeuvreId()!=null && oeuvreQuery.getOeuvreId()!=0){
            Optional<Oeuvre> oldOeuvre= oeuvreRepository.findByOeuvreId(oeuvreQuery.getOeuvreId());
            oldOeuvre.ifPresent(ov -> oeuvre.setOeuvreId(ov.getOeuvreId()));
        }

        Oeuvre savedOeuvre = oeuvreRepository.save(oeuvre);

        log.warn("successfully saved");
        return savedOeuvre;
    }

    public Oeuvre findByTitreOeuvre(String titreOeuvre) {
        Optional<Oeuvre> oldOeuvre = oeuvreRepository.findByTitreOeuvre(titreOeuvre);
        return oldOeuvre.orElse(null);
    }


    public OeuvreTraduction addUpdateOeuvreTraduction(OeuvreTraductionQuery q) {
        OeuvreTraduction toSave =  new OeuvreTraduction( q.getOeuvreTradId(), q.getTitre(), q.getTraductionTitre(), q.getAvantages(),
                q.getGenre(), q.getCodeLangue());

        if(q.getOeuvreId()!=null && q.getOeuvreId()!=0){
            Optional<Oeuvre> oldOeuvre= oeuvreRepository.findByOeuvreId(q.getOeuvreId());
            oldOeuvre.ifPresent(toSave::setOeuvre);
        }else {
            log.info("OeuvreId is not filled...");
            return null;
        }

        if( q.getTitre()!=null && q.getCodeLangue()!=null){
            Optional<OeuvreTraduction> oldOeuvreTraduction = oeuvreTraductionRepository.findByTitreAndCodeLangue(q.getTitre(), q.getCodeLangue());
            oldOeuvreTraduction.ifPresent(oeuvreTraduction -> toSave.setOeuvreTradId(oeuvreTraduction.getOeuvreTradId()));
        }

        return oeuvreTraductionRepository.save(toSave);

    }
}
