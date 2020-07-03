package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Ref_theme")
    private Long refTheme;

    @Column(name="Nom_theme_FR")
    private String nomThemeFR;

    @Column(name="Nom_theme_AR")
    private String nomThemeAR;

    @Column(name="Nom_theme_EN")
    private String nomThemeEN;

    @Column(name="Nom_theme_WL")
    private String nomThemeWL;

    public Theme(Long refTheme, String nomThemeFR, String nomThemeAR, String nomThemeEN, String nomThemeWL) {
        this.refTheme = refTheme;
        this.nomThemeFR = nomThemeFR;
        this.nomThemeAR = nomThemeAR;
        this.nomThemeEN = nomThemeEN;
        this.nomThemeWL = nomThemeWL;
    }
}


