package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class CategoryTraductionQuery {
    private Long categoryTradId;
    private Long categoryId;
    private String name;
    private String codeLangue;
}
