package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class OeuvreTraductionQuery {
    private Long oeuvreTradId;
    private Long oeuvreId;
    private String titre;
    private String traductionTitre;
    private String avantages;
    private String genre;
    private String codeLangue;
}
