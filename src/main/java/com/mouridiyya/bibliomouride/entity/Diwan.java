package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Diwan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Ref_diwan")
    private Long refDiwan;

    @Column(name="Titre_diwan_AR")
    private String titreDiwanAR;

    @Column(name="Titre_diwan_FR")
    private String tttreDiwanFR;

    public Diwan(Long refDiwan, String titreDiwanAR, String tttreDiwanFR) {
        this.refDiwan = refDiwan;
        this.titreDiwanAR = titreDiwanAR;
        this.tttreDiwanFR = tttreDiwanFR;
    }
}


