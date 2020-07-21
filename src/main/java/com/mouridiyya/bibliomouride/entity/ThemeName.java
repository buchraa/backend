
package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ThemeName {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Ref_ThemeName")
    private Long themeNameId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JoinColumn(name = "Ref_theme", referencedColumnName="Ref_theme", nullable = false, foreignKey = @ForeignKey(name="FK_THEME_THEMENAME"))
    private  Theme theme;

    public ThemeName(Long themeNameId, String name, String codeLangue, Theme theme) {
        this.themeNameId = themeNameId;
        this.name = name;
        this.codeLangue = codeLangue;
        this.theme = theme;
    }
}

