package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Ref_Author")
    private Long authorId;

    @Column(name="Nom_Author")
    private String name;

    @Column(name="Bio_Author", length=1024)
    private String bio;

    @Column(name="Hyperlink_Author")
    private String link;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Author_Oeuvre",
            joinColumns = {@JoinColumn(name = "Ref_Author")},
            inverseJoinColumns = {@JoinColumn(name = "Ref_oeuvre")}
    )
    private Set<Oeuvre> oeuvres = new HashSet<>();

    public Author(Long authorId, String name, String bio, String link) {
        this.authorId = authorId;
        this.name = name;
        this.bio = bio;
        this.link = link;
    }
}
