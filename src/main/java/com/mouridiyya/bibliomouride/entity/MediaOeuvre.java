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
    @Column(name = "mediaOeuvreId")
    private Long mediaOeuvreId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oeuvreId", referencedColumnName = "oeuvreId", foreignKey = @ForeignKey(name="FK_MEDIA_OEUVRE_REFOEUVRE"))
    private Oeuvre oeuvre;

    @Column(name="typeMedia")
    private String typeMedia;

    @Column(name="genreMedia")
    private String genreMedia;

    @Column(name="titreMedia", length=512)
    private String titreMedia;

    @Column(name="urlMedia", length=512)
    private String urlMedia;
}


