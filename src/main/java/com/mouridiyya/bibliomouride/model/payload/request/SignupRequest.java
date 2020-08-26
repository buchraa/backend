package com.mouridiyya.bibliomouride.model.payload.request;

import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {

    private String username;
    private String email;
    private Set<String> role;
    private String password;
  

}