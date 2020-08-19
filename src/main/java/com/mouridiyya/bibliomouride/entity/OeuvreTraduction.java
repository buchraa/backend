package com.mouridiyya.bibliomouride.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OeuvreTraduction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="oeuvreTradId")
    private Long oeuvreTradId;

    @Column(name="titre")
    private String titre;

    @Column(name="traductionTitre")
    private String traductionTitre;

    @Column(name="avantages", length=512)
    private String avantages;

    @Column(name="genre")
    private String genre;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "oeuvreId", referencedColumnName="oeuvreId", foreignKey = @ForeignKey(name="FK_OEUVRETRADUCTION_OEUVREID"))
    private Oeuvre oeuvre;


    public OeuvreTraduction(Long oeuvreTradId, String titre, String traductionTitre, String avantages, String genre, String codeLangue) {
        this.oeuvreTradId = oeuvreTradId;
        this.titre = titre;
        this.traductionTitre = traductionTitre;
        this.avantages = avantages;
        this.genre = genre;
        this.codeLangue = codeLangue;
    }
}
