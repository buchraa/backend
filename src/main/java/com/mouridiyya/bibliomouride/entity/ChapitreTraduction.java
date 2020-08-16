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
public class ChapitreTraduction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="chapitreTradId")
    private Long chapitreTradId;

    @Column(name="name")
    private String name;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "chapitreId", referencedColumnName="chapitreId", foreignKey = @ForeignKey(name="FK_CHAPITRETRADUCTION_CHAPITREID"))
    private Chapitre chapitre;

    public ChapitreTraduction(Long chapitreTradId, String name, String codeLangue) {
        this.chapitreTradId = chapitreTradId;
        this.name = name;
        this.codeLangue = codeLangue;
    }
}
