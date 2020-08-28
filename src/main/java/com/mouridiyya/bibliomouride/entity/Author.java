package com.mouridiyya.bibliomouride.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name="authorId")
    private Long authorId;

    @Column(name="name")
    private String name;

    @Column(name="biography", length=1024)
    private String biography;

    @Column(name="imageUrl")
    private String imageUrl;

    @Column(name="hyperlink")
    private String link;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Oeuvre> oeuvres = new HashSet<>();


    public Author(Long authorId, String name, String biography, String link, String imageUrl) {
        this.authorId = authorId;
        this.name = name;
        this.biography = biography;
        this.link = link;
        this.imageUrl = imageUrl;
    }
}
