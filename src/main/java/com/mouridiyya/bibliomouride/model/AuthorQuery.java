package com.mouridiyya.bibliomouride.model;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class AuthorQuery {

    private Long authorId;
    private String name;
    private String bio;
    private String link;

}
