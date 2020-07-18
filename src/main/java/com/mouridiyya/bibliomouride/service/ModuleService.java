package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Diwan;
import com.mouridiyya.bibliomouride.entity.Module;
import com.mouridiyya.bibliomouride.model.ModuleQuery;
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
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Cacheable(cacheNames="findAllModule")
    public List<Module> getModules() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(moduleRepository.findAll());
    }


    @Caching(evict = {
            @CacheEvict(value = "findAllModule", allEntries = true),
            @CacheEvict(value = "findModuleById", allEntries = true)})
    public Module addUpdateModule(ModuleQuery q) {
        Module toSave =  new Module( q.getModuleId(),  q.getModuleCode(),  q.getNameFr(),  q.getNameAr(),  q.getNameEn(),  q.getNameWo(), q.getDispo());
        if(Optional.ofNullable(q.getModuleId()).orElse(null)!=null && q.getModuleId() !=0){
            Optional<Module> oldAuthor = moduleRepository.findById(Optional.ofNullable(q.getModuleId()).orElse(null));
            if(oldAuthor.isPresent()){
                toSave.setModuleId(oldAuthor.get().getModuleId());
            }
        }
        return moduleRepository.save(toSave);
    }

    @Cacheable(cacheNames="findModuleById")
    public Module get(long id) {
        log.info("Connecting to DB...");
        return moduleRepository.findById(id).get();
    }

    public void delete(long id) {
        moduleRepository.deleteById(id);
    }

}
