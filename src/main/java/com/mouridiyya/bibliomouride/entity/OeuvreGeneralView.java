package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "Oeuvre_General_View")
@Getter
@Setter
@NoArgsConstructor
public class OeuvreGeneralView {

    @Id
    @Column(name="row_num")
    private Long rownum;

    @Column(name="oeuvre_id")
    private Long oeuvreId;

    @Column(name = "vers_id")
    private Long versId;

    @Column(name="oeuvre_trad_id")
    private Long oeuvreTradId;

    @Column(name="acrostiche", length=512)
    private String acrostiche;

    @Column(name="premier_vers", length=512)
    private String premierVers;

    @Column(name="titre")
    private String titre;

    @Column(name="titre_oeuvre")
    private String titreOeuvre;

    @Column(name="titre_populaire")
    private String titrePopulaire;

    @Column(name="texte_versar1", length=512)
    private String texteVersAR1;

    @Column(name="texte_versar2", length=512)
    private String texteVersAR2;

    @Column(name="texte_versar3", length=512)
    private String texteVersAR3;

    @Column(name="texte_versar4", length=512)
    private String texteVersAR4;

    @Column(name="titretrad")
    private String titretrad;

    @Column(name="traduction_titre")
    private String traductiontitre;

}
