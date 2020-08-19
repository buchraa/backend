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
public class Chapitre {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="chapitreId")
    private Long chapitreId;

    @Column(name="numChap")
    private Integer num;

    @ManyToOne
    @JoinColumn(name = "oeuvreId", referencedColumnName = "oeuvreId", foreignKey = @ForeignKey(name="FK_CHAPITRE_OEUVRE_REFOEUVRE"))
    private Oeuvre oeuvre;

    @Column(name="typeChap")
    private String chapterType;

    @Column(name="sectionChap")
    private Integer chapterSection;

    @Column(name="themeChap")
    private String theme;

    @Column(name="plageVers")
    private String plageVers;

    @Column(name="titre")
    private String title;

    @Column(name="isAvailable")
    private Boolean isAvailable;

    @OneToMany(
            mappedBy = "chapitre",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<ChapitreTraduction> traductions = Lists.newArrayList();

    public Chapitre(Long chapitreId, Integer num, String chapterType, Integer chapterSection, String title, String theme, String plageVers, Boolean isAvailable) {
        this.chapitreId = chapitreId;
        this.num = num;
        this.chapterType = chapterType;
        this.chapterSection = chapterSection;
        this.title = title;
        this.theme = theme;
        this.plageVers = plageVers;
        this.isAvailable = isAvailable;
    }
}
