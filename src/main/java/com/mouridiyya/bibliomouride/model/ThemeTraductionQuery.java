package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class ThemeTraductionQuery {
    private Long themeId;
    private Long themeTradId;
    private String name;
    private String codeLangue;
}
