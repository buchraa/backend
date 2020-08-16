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
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "themeID")
    private Long themeId;

    @Column(name="name")
    private String name;

    @Column(name="isAvailable")
    private Boolean isAvailable;

    @OneToMany(
            mappedBy = "theme",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<ThemeTraduction> traductions = Lists.newArrayList();

    public Theme(Long themeId, String name, Boolean isAvailable) {
        this.themeId = themeId;
        this.name = name;
        this.isAvailable = isAvailable;
    }

}


