package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class ModuleTraductionQuery {
    private Long moduleTradId;
    private Long moduleId;
    private String name;
    private String codeLangue;
}
