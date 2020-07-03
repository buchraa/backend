package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Module;
import com.mouridiyya.bibliomouride.model.ModuleQuery;
import com.mouridiyya.bibliomouride.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> getModules() {
        return Lists.newArrayList(moduleRepository.findAll());
    }

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

}
