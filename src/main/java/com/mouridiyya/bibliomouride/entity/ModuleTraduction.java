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
public class ModuleTraduction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="moduleTradId")
    private Long moduleTradId;

    @Column(name="name")
    private String name;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "moduleId", referencedColumnName="moduleId", foreignKey = @ForeignKey(name="FK_MODULETRADUCTION_MODULEID"))
    private Module module;

    public ModuleTraduction(Long moduleTradId, String name, String codeLangue) {
        this.moduleTradId = moduleTradId;
        this.name = name;
        this.codeLangue = codeLangue;
    }
}
