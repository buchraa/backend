package com.mouridiyya.bibliomouride.model;


import com.mouridiyya.bibliomouride.entity.Oeuvre;
import lombok.Data;

import javax.persistence.*;

@Data
public class ChapitreQuery {

    private Long chapterId;
    private Integer num;
    private String chapterType;
    private Integer chapterSection;
    private String titleAr;
    private String titleFr;
    private String titleEn;
    private String titleWo;
    private String theme;
    private String vers;
    private Integer dispo;

}
