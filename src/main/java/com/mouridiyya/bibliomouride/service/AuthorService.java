package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Author;
import com.mouridiyya.bibliomouride.model.AuthorQuery;
import com.mouridiyya.bibliomouride.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    @Cacheable(cacheNames="findAllAuthor")
    public List<Author> getAuthors() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(authorRepository.findAll());
    }

    @Caching(evict = {
            @CacheEvict(value = "findAllAuthor", allEntries = true),
            @CacheEvict(value = "findAuthorById", allEntries = true)})
    public Author addUpdateAuthor(AuthorQuery q) {
        Author toSave =  new Author(q.getAuthorId(),  q.getName(),  q.getBiography(), q.getLink(), q.getImageUrl() );
        if(Optional.ofNullable(q.getAuthorId()).orElse(null)!=null && q.getAuthorId() !=0){
            Optional<Author> oldAuthor = authorRepository.findById(Optional.ofNullable(q.getAuthorId()).orElse(null));
            oldAuthor.ifPresent(author -> toSave.setAuthorId(author.getAuthorId()));
        }
        return authorRepository.save(toSave);
    }

    @Cacheable(cacheNames = "findAuthorById")
    public Author get(long id) {
        log.info("Connecting to DB...");
        return authorRepository.findById(id).get();
    }

    @Caching(evict = {
            @CacheEvict(value = "findAllAuthor", allEntries = true),
            @CacheEvict(value = "findAuthorById", allEntries = true)})
    public void delete(long id) {
         authorRepository.deleteById(id);
    }


    public Author getAuthorByName(String name) {
        Optional<Author> oldAuthor= authorRepository.findByName(name);
        return oldAuthor.orElse(null);
    }


}
