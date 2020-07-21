
package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DiwanName {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Ref_DiwanName")
    private Long diwanNameId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JoinColumn(name = "Ref_diwan", referencedColumnName="Ref_diwan", nullable = false, foreignKey = @ForeignKey(name="FK_DIWAN_DIWANNAME"))
    private Diwan diwan;

    public DiwanName(Long diwanNameId, String name, String codeLangue, Diwan diwan) {
        this.diwanNameId = diwanNameId;
        this.name = name;
        this.codeLangue = codeLangue;
        this.diwan = diwan;
    }
}

