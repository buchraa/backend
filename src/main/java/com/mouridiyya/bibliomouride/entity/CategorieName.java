
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

    public CategorieName(Long categorieNameId, String name, String codeLangue) {
        this.categorieNameId = categorieNameId;
        this.name = name;
        this.codeLangue = codeLangue;
    }
}

