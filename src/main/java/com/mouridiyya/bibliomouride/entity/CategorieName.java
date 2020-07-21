
package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CategorieName {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Ref_CategoryName")
    private Long categorieNameId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JoinColumn(name = "Ref_Categ", referencedColumnName="Ref_Categ", nullable = false, foreignKey = @ForeignKey(name="FK_CATEGORY_CAT_CATEGORYNAME"))
    private Categorie categorie;

    public CategorieName(Long categorieNameId, String name, String codeLangue, Categorie categorie) {
        this.categorieNameId = categorieNameId;
        this.name = name;
        this.codeLangue = codeLangue;
        this.categorie = categorie;
    }
}

