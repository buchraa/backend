package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Module;
import com.mouridiyya.bibliomouride.entity.ModuleTraduction;
import com.mouridiyya.bibliomouride.model.ModuleQuery;
import com.mouridiyya.bibliomouride.model.ModuleTraductionQuery;
import com.mouridiyya.bibliomouride.repository.ModuleRepository;
import com.mouridiyya.bibliomouride.repository.ModuleTraductionRepository;
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

    @Autowired
    private ModuleTraductionRepository moduleTraductionRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @Cacheable(cacheNames="findAllModule")
    public List<Module> getModules() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(moduleRepository.findAll());
    }


    @Caching(evict = {
            @CacheEvict(value = "findAllModule", allEntries = true),
            @CacheEvict(value = "findModuleById", allEntries = true)})
    public Module addUpdateModule(ModuleQuery q) {
        Module toSave =  new Module( q.getModuleId(),  q.getName(),  q.getIsAvailable());

        if( q.getName()!=null && !q.getName().isEmpty()){
            Optional<Module> oldModule = moduleRepository.findByName(q.getName());
            oldModule.ifPresent(module -> toSave.setModuleId(module.getModuleId()));
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

    public Module findByName(String name) {
        Optional<Module> oldModule = moduleRepository.findByName(name);
        return oldModule.orElse(null);
    }
    public ModuleTraduction addUpdateModuleTraduction(ModuleTraductionQuery q) {
        ModuleTraduction toSave =  new ModuleTraduction( q.getModuleId(), q.getName(), q.getCodeLangue());

        if(q.getModuleId()!=null && q.getModuleId()!=0){
            Optional<Module> oldModule= moduleRepository.findByModuleId(q.getModuleId());
            oldModule.ifPresent(toSave::setModule);
        }else {
            log.info("moduleId is not filled...");
            return null;
        }

        if( q.getName()!=null && q.getCodeLangue()!=null){
            Optional<ModuleTraduction> oldModuleTraduction = moduleTraductionRepository.findByNameAndCodeLangue(q.getName(), q.getCodeLangue());
            oldModuleTraduction.ifPresent(moduleTraduction -> toSave.setModuleTradId(moduleTraduction.getModuleTradId()));
        }

        return moduleTraductionRepository.save(toSave);

    }
}
