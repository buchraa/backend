package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Author;
import com.mouridiyya.bibliomouride.entity.Categorie;
import com.mouridiyya.bibliomouride.entity.Module;
import com.mouridiyya.bibliomouride.model.CategoryQuery;
import com.mouridiyya.bibliomouride.repository.CategoryRepository;
import com.mouridiyya.bibliomouride.repository.ModuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModuleRepository moduleRepository;


    @Cacheable(cacheNames="findAllCategory")
    public List<Categorie> getCategories() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(categoryRepository.findAll());
    }

    @Caching(evict = {
            @CacheEvict(value = "findAllCategory", allEntries = true),
            @CacheEvict(value = "findCategoryById", allEntries = true)})
    public Categorie addUpdateCategory(CategoryQuery q) {
        Categorie toSave =  new Categorie( q.getCategoryId(),  q.getCategoryCode());

        if(q.getModuleId()!=null && q.getModuleId()!=0){
            Optional<Module> oldModule = moduleRepository.findById(Optional.ofNullable(q.getModuleId()).orElse(null));
            if(oldModule.isPresent()){
                toSave.setModule(oldModule.get());
            }
        }
        if(Optional.ofNullable(q.getCategoryId()).orElse(null)!=null && q.getCategoryId() !=0){
            Optional<Categorie> oldCategory= categoryRepository.findById(Optional.ofNullable(q.getCategoryId()).orElse(null));
            if(oldCategory.isPresent()){
                toSave.setCategoryId(oldCategory.get().getCategoryId());
            }
        }

        return categoryRepository.save(toSave);
    }

    @Cacheable(cacheNames = "findCategoryById")
    public Categorie get(long id) {
        log.info("Connecting to DB...");
        return categoryRepository.findById(id).get();
    }


    @Caching(evict = {
            @CacheEvict(value = "findAllCategory", allEntries = true),
            @CacheEvict(value = "findCategoryById", allEntries = true)})
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }


}
