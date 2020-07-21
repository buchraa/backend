
package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OeuvreName {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Ref_OeuvreName")
    private Long oeuvreNameId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JoinColumn(name = "Ref_oeuvre", referencedColumnName="Ref_oeuvre", nullable = false, foreignKey = @ForeignKey(name="FK_OEUVRE_OEUVRENAME"))
    private  Oeuvre oeuvre;

    public OeuvreName(String name, String codeLangue, Oeuvre oeuvre) {
        this.name = name;
        this.codeLangue = codeLangue;
        this.oeuvre = oeuvre;
    }
}

