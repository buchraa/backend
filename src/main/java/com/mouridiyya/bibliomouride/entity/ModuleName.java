
package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ModuleName {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Ref_ModuleName")
    private Long moduleNameId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JoinColumn(name = "Ref_mod", referencedColumnName="Ref_mod", nullable = false, foreignKey = @ForeignKey(name="FK_MOD_MODULENAME"))
    private  Module module;

    public ModuleName(Long moduleNameId, String name, String codeLangue, Module module) {
        this.moduleNameId = moduleNameId;
        this.name = name;
        this.codeLangue = codeLangue;
        this.module = module;
    }
}

