package com.mouridiyya.bibliomouride.model;


import lombok.Data;

@Data
public class ChapitreQuery {
    private Long oeuvreId;
    private Long chapterId;
    private Integer num;
    private String chapterType;
    private Integer chapterSection;
    private String title;
    private String theme;
    private String plageVers;
    private Boolean isAvailable;

}
