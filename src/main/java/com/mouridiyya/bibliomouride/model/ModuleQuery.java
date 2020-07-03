package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class ModuleQuery {

    private Long moduleId;
    private Integer moduleCode;
    private String nameFr;
    private String nameAr;
    private String nameEn;
    private String nameWo;
    private Integer dispo;
}
