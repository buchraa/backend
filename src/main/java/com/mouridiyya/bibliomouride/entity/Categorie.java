package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Categorie {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Ref_Categ")
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "Ref_mod", referencedColumnName="Ref_mod", nullable = true, foreignKey = @ForeignKey(name="FK_CATEGORY_MODULE_MODULEID"))
    private Module module;

    @Column(name="Code_categ", nullable = false)
    private Integer categoryCode;

    @Column(name="Dispo_categ")
    private Boolean dispoCateg;


    public Categorie(Long categoryId, Module module, Integer categoryCode) {
        this.categoryId = categoryId;
        this.module = module;
        this.categoryCode = categoryCode;
    }

    public Categorie(Long categoryId, Integer categoryCode) {
        this.categoryId = categoryId;
        this.categoryCode = categoryCode;
    }
}
