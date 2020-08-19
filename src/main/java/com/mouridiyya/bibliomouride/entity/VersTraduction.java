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
public class VersTraduction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="versTradId")
    private Long versTradId;

    @Column(name="texte")
    private String texte;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "versId", referencedColumnName="versId", foreignKey = @ForeignKey(name="FK_VERSRADUCTION_VERSID"))
    private Vers vers;


    public VersTraduction(Long versTradId, String texte, String codeLangue) {
        this.versTradId = versTradId;
        this.texte = texte;
        this.codeLangue = codeLangue;
    }
}
