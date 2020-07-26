package com.mouridiyya.bibliomouride.model;



import com.mouridiyya.bibliomouride.entity.Author;
import com.mouridiyya.bibliomouride.entity.Categorie;
import com.mouridiyya.bibliomouride.entity.Diwan;
import com.mouridiyya.bibliomouride.entity.Theme;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class OeuvreQuery {

    private Long refOeuvre;

    private String titreOeuvre;

    private String TitrePopulaire;

    private Boolean disponibiliteOeuvre;

    private Boolean tradFR;

    private Boolean tradEN;

    private Boolean tradWL;

    private Boolean pdfOeuvre;

    private String premierVers;

    private String presentation;

    private String diwanPage;

    private String genre;

    private Integer nbVers;

    private String acrostiche;

    private String metriqueNom;

    private String rime;

    private String periode;

    private String periodeDatation;

    private String PeriodeLieu;

    private String PeriodeRques;

    private String AuthenticiteDegre;

    private String Forme_rques;

    private String Avantages;

    private String modesLecture;

    private String edition;

    private String urlOeuvre;

    private String achatOnline;

    private String Remarques;

}
