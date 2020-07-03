package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Auteur;
import com.mouridiyya.bibliomouride.model.AuthorQuery;
import com.mouridiyya.bibliomouride.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Auteur> getAuthors() {
        return Lists.newArrayList(authorRepository.findAll());
    }

    public Auteur addUpdateAuthor(AuthorQuery q) {
        Auteur toSave =  new Auteur(q.getAuthorId(),  q.getName(),  q.getBio(), q.getLink());
        if(Optional.ofNullable(q.getAuthorId()).orElse(null)!=null && q.getAuthorId() !=0){
            Optional<Auteur> oldAuthor = authorRepository.findById(Optional.ofNullable(q.getAuthorId()).orElse(null));
            if(oldAuthor.isPresent()){
                toSave.setAuthorId(oldAuthor.get().getAuthorId());
            }
        }
        return authorRepository.save(toSave);
    }

}
