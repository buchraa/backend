package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Categorie;
import com.mouridiyya.bibliomouride.entity.Diwan;
import com.mouridiyya.bibliomouride.model.DiwanQuery;
import com.mouridiyya.bibliomouride.repository.DiwanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class  DiwanService {

    @Autowired
    private DiwanRepository diwanRepository;

    @Cacheable(cacheNames="findAllDiwan")
    public List<Diwan> getDiwans() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(diwanRepository.findAll());
    }


    @Caching(evict = {
            @CacheEvict(value = "findAllDiwan", allEntries = true),
            @CacheEvict(value = "findDiwanById", allEntries = true)})
    public Diwan addUpdateDiwan(DiwanQuery q) {
        Diwan toSave =  new Diwan( q.getRefDiwan());
        if(Optional.ofNullable(q.getRefDiwan()).orElse(null)!=null && q.getRefDiwan() !=0){
            Optional<Diwan> oldDiwan = diwanRepository.findById(Optional.ofNullable(q.getRefDiwan()).orElse(null));
            if(oldDiwan.isPresent()){
                toSave.setRefDiwan(oldDiwan.get().getRefDiwan());
            }
        }
        return diwanRepository.save(toSave);
    }

    @Cacheable(cacheNames = "findDiwanById")
    public Diwan get(long id) {
        log.info("Connecting to DB...");
        return diwanRepository.findById(id).get();
    }


    public void delete(long id) {
        diwanRepository.deleteById(id);
    }


}
