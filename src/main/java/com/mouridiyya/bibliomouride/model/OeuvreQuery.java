package com.mouridiyya.bibliomouride.model;

import lombok.Data;

import java.util.Set;

@Data
public class OeuvreQuery {


    private Long oeuvreId;
    private String authenticite_degre;
    private String avantages;
    private String forme_rques;
    private String periode_lieu;
    private String periode_rques;
    private String remarques;
    private String titre_populaire;
    private String achat_online;
    private String acrostiche;
    private Boolean dispo_oeuvre;
    private String diwan_page;
    private String edition;
    private String genre;
    private String metrique_nom;
    private String modes_lecture;
    private Integer nb_vers;
    private Boolean pdf_oeuvre;
    private String periode;
    private String periode_datation;
    private String premier_vers;
    private String presentation;
    private String rime;
    private String titrear;
    private String titreen;
    private String titrefr;
    private String titre_oeuvre;
    private String titrewl;
    private String traden;
    private String tradfr;
    private String tradwl;
    private String url_oeuvre;
    private Long ref_categ;
    private Long diwan_origine;
    private Long theme_principal;
    private Set<Long> refAuthors;
  
}
