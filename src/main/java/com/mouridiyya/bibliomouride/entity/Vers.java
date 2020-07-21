package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Ref_vers")
    private Long refVers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Ref_chap", referencedColumnName = "Ref_chap", nullable = true, foreignKey = @ForeignKey(name="FK_VERS_CHAPITRE_REFCHAP"))
    private Chapitre Chapitre;

    @Column(name="Type_vers")
    private String typeVers;

    @Column(name="Num_vers")
    private Long numVers;

    @Column(name="Ref_vers_note")
    private Long refVersNote;

    @Column(name="Texte_versAR1", length=512)
    private String texteVersAR1;

    @Column(name="Texte_versAR2", length=512)
    private String texteVersAR2;

    @Column(name="Texte_versAR3", length=512)
    private String texteVersAR3;

    @Column(name="Texte_versAR4", length=512)
    private String texteVersAR4;

}


