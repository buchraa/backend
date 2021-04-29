package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class VersQuery {

    private Long versId;
    private Long oeuvreId;
    private Long chapitreId;
    private String typeVers;
    private Integer numVers;
    private Integer refVersNote;
    private String audioVers;
    private String texteVersAR1;
    private String texteVersAR2;
    private String texteVersAR3;
    private String texteVersAR4;

}
