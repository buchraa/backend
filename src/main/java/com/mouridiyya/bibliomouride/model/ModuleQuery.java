package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class ModuleQuery {

    private Long moduleId;
    private String name;
    private Boolean isAvailable;
}
