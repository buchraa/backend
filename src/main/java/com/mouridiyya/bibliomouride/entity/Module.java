package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Module {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Ref_mod")
    private Long moduleId;

    @Column(name="Code_mod", nullable = false)
    private Integer moduleCode;

    @Column(name="Dispo_mod")
    private Integer dispo;

    public Module(Long moduleId, Integer moduleCode, Integer dispo) {
        this.moduleId = moduleId;
        this.moduleCode = moduleCode;
        this.dispo = dispo;
    }
}
