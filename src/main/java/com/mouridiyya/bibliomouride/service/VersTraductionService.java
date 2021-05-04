package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Vers;
import com.mouridiyya.bibliomouride.entity.VersTraduction;
import com.mouridiyya.bibliomouride.model.VersTraductionQuery;
import com.mouridiyya.bibliomouride.model.VersQuery;
import com.mouridiyya.bibliomouride.repository.VersRepository;
import com.mouridiyya.bibliomouride.repository.VersTraductionRepository;
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

public class VersTraductionService {

    @Autowired
    private VersTraductionRepository versTraductionRepository;

    @Autowired
    private VersRepository versRepository;

    public List<VersTraduction> getVersTraduction() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(versTraductionRepository.findAll());
    }


    @Caching(evict = {
            @CacheEvict(value = "findAllVersTraduction", allEntries = true),
            @CacheEvict(value = "findVersTraductionById", allEntries = true)})
    public VersTraduction addUpdateVersTraduction(VersTraductionQuery q) {

        VersTraduction toSave =  new VersTraduction( q.getVersTraductionId(), q.getTexte(), q.getCodeLangue());

        if(q.getVersId()!=null && q.getVersId()!=0){
            Optional<Vers> oldVers = versRepository.findByVersId(Optional.ofNullable(q.getVersId()).orElse(null));
            oldVers.ifPresent(toSave::setVers);
        }
        if(q.getTexte()!=null && !q.getTexte().isEmpty()){
            Optional<VersTraduction> oldVersTraduction= versTraductionRepository.findByTexteAndCodeLangue(q.getTexte(), q.getCodeLangue() );
            oldVersTraduction.ifPresent(versTraduction -> toSave.setVersTraductionId(versTraduction.getVersTraductionId()));
        }
        return versTraductionRepository.save(toSave);
    }


    public void delete(long id) {
        versTraductionRepository.deleteById(id);
    }

    public VersTraduction findById(long id){
        Optional<VersTraduction> oldVersTraduction = versTraductionRepository.findByVersTradId(id);
        return oldVersTraduction.orElse(null);
    }
}
