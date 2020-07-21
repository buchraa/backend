
package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VersName {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Ref_VersName")
    private Long VersNameId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JoinColumn(name = "Ref_vers", referencedColumnName="Ref_vers", nullable = false, foreignKey = @ForeignKey(name="FK_VERS_VERSNAME"))
    private  Vers vers;

    public VersName(Long versNameId, String name, String codeLangue, Vers vers) {
        VersNameId = versNameId;
        this.name = name;
        this.codeLangue = codeLangue;
        this.vers = vers;
    }
}

