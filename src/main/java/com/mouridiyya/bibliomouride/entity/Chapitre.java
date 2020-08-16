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

    @Column(name="Num_chap")
    private Integer num;

    @ManyToOne
    @JoinColumn(name = "oeuvreId", referencedColumnName = "oeuvreId", foreignKey = @ForeignKey(name="FK_CHAPITRE_OEUVRE_REFOEUVRE"))
    private Oeuvre oeuvre;

    @Column(name="Type_chap")
    private String chapterType;

    @Column(name="Section_chap")
    private Integer chapterSection;

    @Column(name="Theme_chap")
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
