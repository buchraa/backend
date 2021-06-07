package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Categorie;
import com.mouridiyya.bibliomouride.entity.CategorieTraduction;
import com.mouridiyya.bibliomouride.entity.Module;
import com.mouridiyya.bibliomouride.model.CategoryQuery;
import com.mouridiyya.bibliomouride.model.CategoryTraductionQuery;
import com.mouridiyya.bibliomouride.repository.CategorieTraductionRepository;
import com.mouridiyya.bibliomouride.repository.CategoryRepository;
import com.mouridiyya.bibliomouride.repository.ModuleRepository;
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
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private CategorieTraductionRepository categorieTraductionRepository;

    @Cacheable(cacheNames="findAllCategory")
    public List<Categorie> getCategories() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(categoryRepository.findAll());
    }
    
    
    @Cacheable(cacheNames="findAllCategory")
    public List<Categorie> getCategoriesForModule(long id) {
        
        return Lists.newArrayList(categoryRepository.findByModuleId(id));
    }

    @Caching(evict = {
            @CacheEvict(value = "findAllCategory", allEntries = true),
            @CacheEvict(value = "findCategoryById", allEntries = true)})
    public Categorie addUpdateCategory(CategoryQuery q) {

        Categorie toSave =  new Categorie( q.getCategoryId(), q.getName(), q.getIsAvailable());

        if(q.getModuleId()!=null && q.getModuleId()!=0){
            Optional<Module> oldModule = moduleRepository.findById(Optional.ofNullable(q.getModuleId()).orElse(null));
            oldModule.ifPresent(toSave::setModule);
        }
        if(q.getName()!=null && !q.getName().isEmpty()){
            Optional<Categorie> oldCategory= categoryRepository.findByName(q.getName()); //add search wth moduleID for unicity
            oldCategory.ifPresent(categorie -> toSave.setCategoryId(categorie.getCategoryId()));

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


    public Categorie findByName(String name) {
        Optional<Categorie> oldCategorie = categoryRepository.findByName(name);
        return oldCategorie.orElse(null);
    }



    public CategorieTraduction addUpdateCategoryTraduction(CategoryTraductionQuery q) {
        CategorieTraduction toSave =  new CategorieTraduction( q.getCategoryTradId(), q.getName(), q.getCodeLangue());

        if(q.getCategoryId()!=null && q.getCategoryId()!=0){
            Optional<Categorie> oldCategory = categoryRepository.findByCategoryId(q.getCategoryId());
            oldCategory.ifPresent(toSave::setCategorie);
        }else {
            log.info("CategoryId is not filled...");
            return null;
        }

        if( q.getName()!=null && q.getCodeLangue()!=null){
            Optional<CategorieTraduction> oldCategorieTraduction = categorieTraductionRepository.findByNameAndCodeLangue(q.getName(), q.getCodeLangue());
            oldCategorieTraduction.ifPresent(categorieTraduction -> toSave.setCategoryTradId(categorieTraduction.getCategoryTradId()));
        }

        return categorieTraductionRepository.save(toSave);

    }
}
