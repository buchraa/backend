package com.mouridiyya.bibliomouride.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DiwanTraduction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="diwanTradId")
    private Long diwanTradId;

    @Column(name="name")
    private String name;

    @Column(name="codeLangue")
    private String codeLangue;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "diwanId", referencedColumnName="diwanId", foreignKey = @ForeignKey(name="FK_DIWANTRADUCTION_DIWANID"))
    private Diwan diwan;

    public DiwanTraduction(Long diwanTradId, String name, String codeLangue) {
        this.diwanTradId = diwanTradId;
        this.name = name;
        this.codeLangue = codeLangue;
    }
}
