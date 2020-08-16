package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MediaOeuvre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Ref_oeuvre_media")
    private Long refOeuvreMedia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oeuvreId", referencedColumnName = "oeuvreId", foreignKey = @ForeignKey(name="FK_MEDIA_OEUVRE_REFOEUVRE"))
    private Oeuvre oeuvre;

    @Column(name="Type_media")
    private String typeMedia;

    @Column(name="Genre_media")
    private String genreMedia;

    @Column(name="Titre_media", length=512)
    private String titreMedia;

    @Column(name="URL_media", length=512)
    private String urlMedia;
}


