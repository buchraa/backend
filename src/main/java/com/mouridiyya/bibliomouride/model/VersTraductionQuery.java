package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class VersTraductionQuery {
    private Long versTradId;
    private Long versId;
    private String texte;
    private String codeLangue;
}
