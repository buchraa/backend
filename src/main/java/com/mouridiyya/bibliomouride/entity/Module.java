package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Module {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Ref_mod")
    private Long moduleId;

    @Column(name="Code_mod", nullable = false)
    private Integer moduleCode;

    @Column(name="Nom_mod_FR")
    private String nameFr;

    @Column(name="Nom_mod_AR")
    private String nameAr;

    @Column(name="Nom_mod_EN")
    private String nameEn;

    @Column(name="Nom_mod_WL")
    private String nameWo;

    @Column(name="Dispo_mod")
    private Integer dispo;

    public Module(Long moduleId, Integer moduleCode, String nameFr, String nameAr, String nameEn, String nameWo, Integer dispo) {
        this.moduleId = moduleId;
        this.moduleCode = moduleCode;
        this.nameFr = nameFr;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.nameWo = nameWo;
        this.dispo = dispo;
    }
}
