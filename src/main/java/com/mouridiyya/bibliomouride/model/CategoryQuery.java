package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class CategoryQuery {

    private Long categoryId;
    private Long moduleId;
    private String name;
    private Boolean isAvailable;
}
