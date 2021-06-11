package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.Module;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ModuleRepository extends CrudRepository<Module, Long> {
    Optional<Module> findByModuleId(Long moduleId);
    Optional<Module> findByName(String name);
    
    
}
