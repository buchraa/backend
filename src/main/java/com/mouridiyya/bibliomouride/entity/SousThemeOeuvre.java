package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SousThemeOeuvre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Ref_oeuvre_theme")
    private Long refOeuvreTheme;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Ref_oeuvre", referencedColumnName = "Ref_oeuvre", nullable = true, foreignKey = @ForeignKey(name="FK_SOUSTHEME_OEUVRE_REFOEUVRE"))
    private Oeuvre oeuvre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Ref_theme", referencedColumnName = "Ref_theme", nullable = true, foreignKey = @ForeignKey(name="FK_SOUSTHEME_OEUVRE_REFTHEME"))
    private Theme refTheme;
}


