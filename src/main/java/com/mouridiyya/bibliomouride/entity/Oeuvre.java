package com.mouridiyya.bibliomouride.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name="oeuvreId")
    private Long oeuvreId;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName="categoryId", foreignKey = @ForeignKey(name="FK_BOOK_CATEGORY_CATEGORYID"))
    private Categorie category;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(
            name = "Author_Oeuvre",
            joinColumns = {@JoinColumn(name = "Ref_Author")},
            inverseJoinColumns = {@JoinColumn(name = "oeuvreId")}
    )
    @JsonManagedReference
    private Set<Author> authors = new HashSet<>();


    @Column(name="Titre_oeuvre")
    private String titreOeuvre;

    @Column(name="Titre_populaire")
    private String titrePopulaire;

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

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "themeId", referencedColumnName = "themeId", foreignKey = @ForeignKey(name="FK_OEUVRE_THEME_REFTHEME"))
    private Theme themePrincipal;

    @Column(name="Presentation", length=512)
    private String presentation;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "diwanId", referencedColumnName = "diwanId", foreignKey = @ForeignKey(name="FK_OEUVRE_DIWAN_REFDIWAN"))
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
    private String periodeLieu;

    @Column(name="Periode_rques", length=512)
    private String periodeRques;

    @Column(name="Authenticite_degre")
    private String authenticiteDegre;

    @Column(name="Forme_rques", length=512)
    private String forme_rques;

    @Column(name="Avantages", length=512)
    private String avantages;

    @Column(name="Modes_lecture", length=512)
    private String modesLecture;

    @Column(name="Edition", length=512)
    private String edition;

    @Column(name="URL_oeuvre", length=512)
    private String urlOeuvre;

    @Column(name="Achat_online", length=512)
    private String achatOnline;

    @Column(name="Remarques", length=512)
    private String remarques;

    public Oeuvre(Long oeuvreId) {
        this.oeuvreId = oeuvreId;
    }


    public Oeuvre(Long oeuvreId, Categorie category, Set<Author> authors, String titreOeuvre, String titrePopulaire, String titreAR, String titreFR, String titreEN, String titreWL, Boolean disponibiliteOeuvre, String tradFR, String tradEN, String tradWL, Boolean pdfOeuvre, String premierVers, Theme themePrincipal, String presentation, Diwan diwanOrigine, String diwanPage, String genre, Integer nbVers, String acrostiche, String metriqueNom, String rime, String periode, String periodeDatation, String periodeLieu, String periodeRques, String authenticiteDegre, String forme_rques, String avantages, String modesLecture, String edition, String urlOeuvre, String achatOnline, String remarques) {
        this.oeuvreId = oeuvreId;
        this.category = category;
        this.authors = authors;
        this.titreOeuvre = titreOeuvre;
        this.titrePopulaire = titrePopulaire;
        this.titreAR = titreAR;
        this.titreFR = titreFR;
        this.titreEN = titreEN;
        this.titreWL = titreWL;
        this.disponibiliteOeuvre = disponibiliteOeuvre;
        this.tradFR = tradFR;
        this.tradEN = tradEN;
        this.tradWL = tradWL;
        this.pdfOeuvre = pdfOeuvre;
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
        this.forme_rques = forme_rques;
        this.avantages = avantages;
        this.modesLecture = modesLecture;
        this.edition = edition;
        this.urlOeuvre = urlOeuvre;
        this.achatOnline = achatOnline;
        this.remarques = remarques;
    }
}
