package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MediaVers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Ref_vers_media")
    private Long refVersMedia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Ref_vers", referencedColumnName = "Ref_vers", nullable = true, foreignKey = @ForeignKey(name="FK_MEDIA_VERS_REFVERS"))
    private Vers vers;

    @Column(name="Type_media")
    private String typeMedia;

    @Column(name="Genre_media")
    private String genreMdia;

    @Column(name="Titre_media", length=512)
    private String titreMedia;

    @Column(name="URL_media", length=512)
    private String urlMedia;
}


