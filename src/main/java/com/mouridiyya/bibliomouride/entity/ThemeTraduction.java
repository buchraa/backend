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
public class ThemeTraduction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="themeTradId")
    private Long themeTradId;

    @Column(name="name")
    private String name;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "themeId", referencedColumnName="themeId", foreignKey = @ForeignKey(name="FK_THEMTRADUCTION_THEMID"))
    private Theme theme;

    public ThemeTraduction(Long themeTradId, String name, String codeLangue) {
        this.themeTradId = themeTradId;
        this.name = name;
        this.codeLangue = codeLangue;
    }
}
