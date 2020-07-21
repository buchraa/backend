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

    @Column(name="Theme_chap")
    private String theme;

    @Column(name="Vers_chap")
    private String vers;

    @Column(name="Dispo_chap")
    private Integer dispo;

    public Chapitre(Long chapterId, Integer num, String chapterType, Integer chapterSection, String theme, String vers, Integer dispo) {
        this.chapterId = chapterId;
        this.num = num;
        this.chapterType = chapterType;
        this.chapterSection = chapterSection;
        this.theme = theme;
        this.vers = vers;
        this.dispo = dispo;
    }
}
