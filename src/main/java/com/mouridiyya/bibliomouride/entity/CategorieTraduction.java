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
public class CategorieTraduction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="categoryTradId")
    private Long categoryTradId;

    @Column(name="name")
    private String name;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "categoryId", referencedColumnName="categoryId", foreignKey = @ForeignKey(name="FK_CATEGORYTRADUCTION_CATEGORYID"))
    private Categorie categorie;

    public CategorieTraduction(Long categoryTradId, String name, String codeLangue) {
        this.categoryTradId = categoryTradId;
        this.name = name;
        this.codeLangue = codeLangue;
    }
}
