package com.mouridiyya.bibliomouride.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Categorie {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="categoryId")
    private Long categoryId;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "moduleId", referencedColumnName="moduleId", foreignKey = @ForeignKey(name="FK_CATEGORY_MODULE_MODULEID"))
    private Module module;

    @Column(name="isAvailable")
    private Boolean isAvailable;

    @OneToMany(
            mappedBy = "categorie",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<CategorieTraduction> traductions = Lists.newArrayList();


    public Categorie(Long categoryId, String name, Boolean isAvailable) {
        this.categoryId = categoryId;
        this.name = name;
        this.isAvailable = isAvailable;
    }
}
