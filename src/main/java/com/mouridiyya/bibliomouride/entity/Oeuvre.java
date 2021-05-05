package com.mouridiyya.bibliomouride.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Oeuvre {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="oeuvreId")
    private Long oeuvreId;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName="categoryId", foreignKey = @ForeignKey(name="FK_BOOK_CATEGORY_CATEGORYID"))
    private Categorie category;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(
            name = "Author_Oeuvre",
            joinColumns = {@JoinColumn(name = "oeuvreId")},
            inverseJoinColumns = {@JoinColumn(name = "authorId  ")}
    )
    @JsonManagedReference
    private Set<Author> authors = new HashSet<>();


    @Column(name="titreOeuvre")
    private String titreOeuvre;

    @Column(name="titrePopulaire")
    private String titrePopulaire;

    @Column(name="titre")
    private String titre;

    @Column(name="dispo_oeuvre")
    private Boolean dispo_oeuvre;

    @Column(name="isPdfOeuvre")
    private Boolean isPdfOeuvre;

    @Column(name="premierVers", length=512)
    private String premierVers;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "themeId", referencedColumnName = "themeId", foreignKey = @ForeignKey(name="FK_OEUVRE_THEME_REFTHEME"))
    private Theme themePrincipal;

    @Column(name="presentation", length=512)
    private String presentation;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "diwanId", referencedColumnName = "diwanId", foreignKey = @ForeignKey(name="FK_OEUVRE_DIWAN_REFDIWAN"))
    private Diwan diwanOrigine;

    @Column(name="diwanPage")
    private String diwanPage;

    @Column(name="genre")
    private String genre;

    @Column(name="nbVers")
    private Integer nbVers;

    @Column(name="acrostiche", length=512)
    private String acrostiche;

    @Column(name="metriqueNom")
    private String metriqueNom;

    @Column(name="rime")
    private String rime;

    @Column(name="periode")
    private String periode;

    @Column(name="periodeDatation")
    private String periodeDatation;

    @Column(name="periodeLieu")
    private String periodeLieu;

    @Column(name="periodeRques", length=512)
    private String periodeRques;

    @Column(name="authenticiteDegre")
    private String authenticiteDegre;

    @Column(name="formeRques", length=512)
    private String formeRques;

    @Column(name="avantages", length=512)
    private String avantages;

    @Column(name="audioOeuvre", length=512)
    private String audioOeuvre;

    @Column(name="modesLecture", length=512)
    private String modesLecture;

    @Column(name="edition", length=512)
    private String edition;

    @Column(name="urlOeuvre", length=512)
    private String urlOeuvre;

    @Column(name="achatOnline", length=512)
    private String achatOnline;

    @Column(name="Remarques", length=512)
    private String remarques;

    public Oeuvre(Long oeuvreId) {
        this.oeuvreId = oeuvreId;
    }

    @OneToMany(
            mappedBy = "oeuvre",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<OeuvreTraduction> traductions = Lists.newArrayList();


    public Oeuvre(Long oeuvreId, Categorie category, Set<Author> authors, String titreOeuvre, String titrePopulaire, String titre,
                  Boolean dispo_oeuvre, Boolean isPdfOeuvre, String premierVers, Theme themePrincipal, String presentation, Diwan diwanOrigine,
                  String diwanPage, String genre, Integer nbVers, String acrostiche, String metriqueNom, String rime, String periode,
                  String periodeDatation, String periodeLieu, String periodeRques, String authenticiteDegre, String formeRques,
                  String avantages, String modesLecture, String edition, String urlOeuvre, String achatOnline, String remarques,
                  String audioOeuvre) {
        this.oeuvreId = oeuvreId;
        this.category = category;
        this.authors = authors;
        this.titreOeuvre = titreOeuvre;
        this.titrePopulaire = titrePopulaire;
        this.titre = titre;
        this.dispo_oeuvre = dispo_oeuvre;
        this.isPdfOeuvre = isPdfOeuvre;
        this.premierVers = premierVers;
        this.themePrincipal = themePrincipal;
        this.presentation = presentation;
        this.diwanOrigine = diwanOrigine;
        this.diwanPage = diwanPage;
        this.genre = genre;
        this.nbVers = nbVers;
        this.acrostiche = acrostiche;
        this.metriqueNom = metriqueNom;
        this.rime = rime;
        this.periode = periode;
        this.periodeDatation = periodeDatation;
        this.periodeLieu = periodeLieu;
        this.periodeRques = periodeRques;
        this.authenticiteDegre = authenticiteDegre;
        this.formeRques = formeRques;
        this.avantages = avantages;
        this.modesLecture = modesLecture;
        this.edition = edition;
        this.urlOeuvre = urlOeuvre;
        this.achatOnline = achatOnline;
        this.remarques = remarques;
        this.audioOeuvre = audioOeuvre;
    }
}
