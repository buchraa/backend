package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class DiwanTraductionQuery {
    private Long diwanId;
    private Long diwanTradId;
    private String name;
    private String codeLangue;
}
