package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Author;
import com.mouridiyya.bibliomouride.model.AuthorQuery;
import com.mouridiyya.bibliomouride.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;



import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@CacheConfig(cacheNames = "auteurCache")
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    @Cacheable(cacheNames="findAllCache")
    public List<Author> getAuthors() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(authorRepository.findAll());
    }

    @Caching(evict = {
            @CacheEvict(value = "findAllCache", allEntries = true),
            @CacheEvict(value = "findByIdCache", allEntries = true)})
    public Author addUpdateAuthor(AuthorQuery q) {
        Author toSave =  new Author(q.getAuthorId(),  q.getName(),  q.getBio(), q.getLink());
        if(Optional.ofNullable(q.getAuthorId()).orElse(null)!=null && q.getAuthorId() !=0){
            Optional<Author> oldAuthor = authorRepository.findById(Optional.ofNullable(q.getAuthorId()).orElse(null));
            if(oldAuthor.isPresent()){
                toSave.setAuthorId(oldAuthor.get().getAuthorId());
            }
        }
        return authorRepository.save(toSave);
    }

    @Cacheable(cacheNames = "findByIdCache")
    public Author get(long id) {
        log.info("Connecting to DB...");
        return authorRepository.findById(id).get();
    }

    public void delete(long id) {
         authorRepository.deleteById(id);
    }

    @Autowired
    CacheManager cacheManager;
     public void flushCache() {
         for (String cacheName : cacheManager.getCacheNames()){
             cacheManager.getCache(cacheName).clear();
             log.info("flushing: " + cacheName);
         }
     }





}
