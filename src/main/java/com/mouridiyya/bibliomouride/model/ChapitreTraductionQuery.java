package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class ChapitreTraductionQuery {
    private Long chapitreTradId;
    private Long chapitreId;
    private String name;
    private String codeLangue;
}
