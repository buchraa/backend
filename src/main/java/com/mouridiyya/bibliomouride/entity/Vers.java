package com.mouridiyya.bibliomouride.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "versId")
    private Long versId;

    @ManyToOne
    @JoinColumn(name = "oeuvreId", referencedColumnName="oeuvreId", foreignKey = @ForeignKey(name="FK_VERS_OEUVRE_OEUVREID"))
    private Oeuvre oeuvre;

    @OneToOne
    @JoinColumn(name = "chapitreId", referencedColumnName = "chapitreId", foreignKey = @ForeignKey(name="FK_VERS_CHAPITRE_REFCHAP"))
    private Chapitre Chapitre;

    @Column(name="Type_vers")
    private String typeVers;

    @Column(name="Num_vers")
    private Integer numVers;

    @Column(name="Ref_vers_note")
    private Integer refVersNote;

    @Column(name="audioVers", length=512)
    private String audioVers;

    @Column(name="Texte_versAR1", length=512)
    private String texteVersAR1;

    @Column(name="Texte_versAR2", length=512)
    private String texteVersAR2;

    @Column(name="Texte_versAR3", length=512)
    private String texteVersAR3;

    @Column(name="Texte_versAR4", length=512)
    private String texteVersAR4;

    @OneToMany(
            mappedBy = "vers",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<VersTraduction> traductions = Lists.newArrayList();


    public Vers(Long versId, String typeVers, Integer numVers, Integer refVersNote, String audioVers, String texteVersAR1, String texteVersAR2, String texteVersAR3, String texteVersAR4) {
        this.versId = versId;
        this.typeVers = typeVers;
        this.numVers = numVers;
        this.refVersNote = refVersNote;
        this.audioVers = audioVers;
        this.texteVersAR1 = texteVersAR1;
        this.texteVersAR2 = texteVersAR2;
        this.texteVersAR3 = texteVersAR3;
        this.texteVersAR4 = texteVersAR4;
    }
}


