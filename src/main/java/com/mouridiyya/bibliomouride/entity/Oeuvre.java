package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Oeuvre {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Ref_oeuvre")
    private Long refOeuvre;

    @ManyToOne
    @JoinColumn(name = "Ref_categ", referencedColumnName="Ref_categ", nullable = true, foreignKey = @ForeignKey(name="FK_BOOK_CATEGORY_CATEGORYID"))
    private Categorie category;


    @ManyToMany(mappedBy = "oeuvres", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Auteur> auteurs = new HashSet<>();


    @Column(name="Titre_oeuvre")
    private String titreOeuvre;

    @Column(name="Titre_populaire")
    private String TitrePopulaire;

    @Column(name="TitreAR")
    private String titreAR;

    @Column(name="TitreFR")
    private String titreFR;

    @Column(name="TitreEN")
    private String titreEN;

    @Column(name="TitreWL")
    private String titreWL;

    @Column(name="Dispo_oeuvre")
    private Boolean disponibiliteOeuvre;

    @Column(name="TradFR")
    private String tradFR;

    @Column(name="TradEN")
    private String tradEN;

    @Column(name="TradWL")
    private String tradWL;

    @Column(name="PDF_oeuvre")
    private Boolean pdfOeuvre;

    @Column(name="Premier_vers", length=512)
    private String premierVers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Theme_principal", referencedColumnName = "Ref_theme", nullable = true, foreignKey = @ForeignKey(name="FK_OEUVRE_THEME_REFTHEME"))
    private Theme themePrincipal;

    @Column(name="Presentation", length=512)
    private String presentation;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Diwan_origine", referencedColumnName = "Ref_diwan", nullable = true, foreignKey = @ForeignKey(name="FK_OEUVRE_DIWAN_REFDIWAN"))
    private Diwan diwanOrigine;

    @Column(name="Diwan_page")
    private String diwanPage;

    @Column(name="Genre")
    private String genre;

    @Column(name="Nb_vers")
    private Integer nbVers;

    @Column(name="Acrostiche", length=512)
    private String acrostiche;

    @Column(name="Metrique_nom")
    private String metriqueNom;

    @Column(name="Rime")
    private String rime;

    @Column(name="Periode")
    private String periode;

    @Column(name="Periode_datation")
    private String periodeDatation;

    @Column(name="Periode_lieu")
    private String PeriodeLieu;

    @Column(name="Periode_rques", length=512)
    private String PeriodeRques;

    @Column(name="Authenticite_degre")
    private String AuthenticiteDegre;

    @Column(name="Forme_rques", length=512)
    private String Forme_rques;

    @Column(name="Avantages", length=512)
    private String Avantages;

    @Column(name="Modes_lecture", length=512)
    private String modesLecture;

    @Column(name="Edition", length=512)
    private String edition;

    @Column(name="URL_oeuvre", length=512)
    private String urlOeuvre;

    @Column(name="Achat_online", length=512)
    private String achatOnline;

    @Column(name="Remarques", length=512)
    private String Remarques;

}
