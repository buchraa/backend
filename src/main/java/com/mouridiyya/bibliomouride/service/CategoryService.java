package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Categorie;
import com.mouridiyya.bibliomouride.entity.Module;
import com.mouridiyya.bibliomouride.model.CategoryQuery;
import com.mouridiyya.bibliomouride.repository.CategoryRepository;
import com.mouridiyya.bibliomouride.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Categorie> getCategories() {
        return Lists.newArrayList(categoryRepository.findAll());
    }

    public Categorie addUpdateCategory(CategoryQuery q) {
        Categorie toSave =  new Categorie( q.getCategoryId(),  q.getCategoryCode(),  q.getNameFr(),  q.getNameAr(),  q.getNameEn(),  q.getNameWo());

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

}
