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
public class Module {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="moduleId")
    private Long moduleId;

    @Column(name="name")
    private String name;

    @Column(name="isAvailable")
    private Boolean isAvailable;

    @OneToMany(
            mappedBy = "module",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<ModuleTraduction> traductions = Lists.newArrayList();

    public Module(Long moduleId, String name, Boolean isAvailable) {
        this.moduleId = moduleId;
        this.name = name;
        this.isAvailable = isAvailable;
    }
}
