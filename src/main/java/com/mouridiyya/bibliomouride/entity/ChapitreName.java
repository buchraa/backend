
package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChapitreName {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Ref_ChapitreName")
    private Long chapitreNameId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JoinColumn(name = "Ref_chap", referencedColumnName="Ref_chap", nullable = false, foreignKey = @ForeignKey(name="FK_CHAPITRE_CHAP_CHAPITRENAME"))
    private Chapitre chapitre;

    public ChapitreName(Long chapitreNameId, String name, String codeLangue, Chapitre chapitre) {
        this.chapitreNameId = chapitreNameId;
        this.name = name;
        this.codeLangue = codeLangue;
        this.chapitre = chapitre;
    }
}

