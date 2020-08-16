package com.mouridiyya.bibliomouride.model;

import lombok.Data;

@Data
public class AuthorQuery {

    private Long authorId;
    private String name;
    private String bio;
    private String link;

}
