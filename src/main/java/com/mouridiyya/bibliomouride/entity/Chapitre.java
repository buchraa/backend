package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Chapitre {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Ref_chap")
    private Long chapterId;

    @Column(name="Num_chap")
    private Integer num;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Ref_oeuvre", referencedColumnName = "Ref_oeuvre", nullable = true, foreignKey = @ForeignKey(name="FK_CHAPITRE_OEUVRE_REFOEUVRE"))
    private Oeuvre oeuvre;

    @Column(name="Type_chap")
    private String chapterType;

    @Column(name="Section_chap")
    private Integer chapterSection;

    @Column(name="Titre_chapAR")
    private String titleAr;

    @Column(name="Titre_chapFR")
    private String titleFr;

    @Column(name="Titre_chapEN")
    private String titleEn;

    @Column(name="Titre_chapWL")
    private String titleWo;

    @Column(name="Theme_chap")
    private String theme;

    @Column(name="Vers_chap")
    private String vers;

    @Column(name="Dispo_chap")
    private Integer dispo;

    public Chapitre(Long chapterId, Integer num, String chapterType, Integer chapterSection, String titleAr, String titleFr, String titleEn, String titleWo, String theme, String vers, Integer dispo) {
        this.chapterId = chapterId;
        this.num = num;
        this.chapterType = chapterType;
        this.chapterSection = chapterSection;
        this.titleAr = titleAr;
        this.titleFr = titleFr;
        this.titleEn = titleEn;
        this.titleWo = titleWo;
        this.theme = theme;
        this.vers = vers;
        this.dispo = dispo;
    }
}
