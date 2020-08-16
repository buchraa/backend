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
public class Diwan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diwanId")
    private Long diwanId;

    @Column(name="name")
    private String name;

    @Column(name="isAvailable")
    private Boolean isAvailable;

    @OneToMany(
            mappedBy = "diwan",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<DiwanTraduction> traductions = Lists.newArrayList();


    public Diwan(Long diwanId, String name, Boolean isAvailable) {
        this.diwanId = diwanId;
        this.name = name;
        this.isAvailable = isAvailable;
    }
}


