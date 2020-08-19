package com.mouridiyya.bibliomouride.model;

import lombok.Data;

import java.util.Set;

@Data
public class OeuvreQuery {

    private Long oeuvreId;
    private String authenticite_degre;
    private String avantages;
    private String formeRques;
    private String periodeLieu;
    private String periodeRques;
    private String remarques;
    private String titrePopulaire;
    private String achatOnline;
    private String acrostiche;
    private Boolean dispo_oeuvre;
    private String diwanPage;
    private String edition;
    private String genre;
    private String metriqueNom;
    private String modesLecture;
    private Integer nbVers;
    private Boolean pdfOeuvre;
    private String periode;
    private String periodeDatation;
    private String premierVers;
    private String presentation;
    private String rime;
    private String titre;
    private String titreOeuvre;
    private String urlOeuvre;
    private Long categoryId;
    private Long diwanOrigine;
    private Long themePrincipal;
    private Set<Long> refAuthors;
  
}
