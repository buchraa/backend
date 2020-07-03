package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class CategoryQuery {

    private Long categoryId;
    private Long moduleId;
    private Integer categoryCode;
    private String nameFr;
    private String nameAr;
    private String nameEn;
    private String nameWo;

}
