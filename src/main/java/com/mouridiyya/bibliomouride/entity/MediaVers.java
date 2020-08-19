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
    @Column(name = "mediaVersId")
    private Long mediaVersId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "versId", referencedColumnName = "versId", foreignKey = @ForeignKey(name="FK_MEDIA_VERS_REFVERS"))
    private Vers vers;

    @Column(name="typeMedia")
    private String typeMedia;

    @Column(name="genreMdia")
    private String genreMdia;

    @Column(name="titreMedia", length=512)
    private String titreMedia;

    @Column(name="urlMedia", length=512)
    private String urlMedia;
}


