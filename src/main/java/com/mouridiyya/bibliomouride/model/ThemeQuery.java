package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class ThemeQuery {

    private Long themeId;
    private String name;
    private Boolean isAvailable;

}
