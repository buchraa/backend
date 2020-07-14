package com.mouridiyya.bibliomouride.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TraductionOeuvre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Ref_trad")
    private Long Ref_trad;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Ref_oeuvre", referencedColumnName = "Ref_oeuvre", nullable = true, foreignKey = @ForeignKey(name="FK_TRADUCTION_OEUVRE_REFOEUVRE"))
    private Oeuvre oeuvre;

    @Column(name="Lg_trad")
    private String lgTrad;

    @Column(name="AuthorTrad")
    private String AuthorTrad;

}


