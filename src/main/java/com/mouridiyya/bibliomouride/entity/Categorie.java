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

    @ManyToOne
    @JoinColumn(name = "Ref_CategoryName", referencedColumnName="Ref_CategoryName", nullable = true, foreignKey = @ForeignKey(name="FK_CATEGORY_CAT_CATEGORYNAME"))
    private CategorieName categorieName;


    @Column(name="Code_categ", nullable = false)
    private Integer categoryCode;

    @Column(name="Nom_Json" )
    private String nameJson;

    @Column(name="Nom_categ_FR" )
    private String nameFr;

    @Column(name="Nom_categ_AR")
    private String nameAr;

    @Column(name="Nom_categ_EN")
    private String nameEn;

    @Column(name="Nom_categ_WL")
    private String nameWo;

    @Column(name="Dispo_categ")
    private Boolean dispoCateg;

    public Categorie(Long categoryId, Integer categoryCode, String nameFr, String nameAr, String nameEn, String nameWo) {
        this.categoryId = categoryId;
        this.module = null;
        this.categoryCode = categoryCode;
        this.nameFr = nameFr;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.nameWo = nameWo;
    }
}
