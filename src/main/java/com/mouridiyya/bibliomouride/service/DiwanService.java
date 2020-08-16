package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Diwan;
import com.mouridiyya.bibliomouride.entity.DiwanTraduction;
import com.mouridiyya.bibliomouride.model.DiwanQuery;
import com.mouridiyya.bibliomouride.model.DiwanTraductionQuery;
import com.mouridiyya.bibliomouride.repository.DiwanRepository;
import com.mouridiyya.bibliomouride.repository.DiwanTraductionRepository;
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
public class  DiwanService {

    @Autowired
    private DiwanRepository diwanRepository;

    @Autowired
    private DiwanTraductionRepository diwanTraductionRepository;

    @Cacheable(cacheNames="findAllDiwan")
    public List<Diwan> getDiwans() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(diwanRepository.findAll());
    }


    @Caching(evict = {
            @CacheEvict(value = "findAllDiwan", allEntries = true),
            @CacheEvict(value = "findDiwanById", allEntries = true)})
    public Diwan addUpdateDiwan(DiwanQuery q) {
        Diwan toSave =  new Diwan( q.getDiwanId(),  q.getName(),  q.getIsAvailable());
        if( q.getName()!=null && !q.getName().isEmpty()){
            Optional<Diwan> oldDiwan = diwanRepository.findByName(q.getName());
            oldDiwan.ifPresent(diwan -> toSave.setDiwanId(diwan.getDiwanId()));
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

    public Diwan findByName(String name) {
        Optional<Diwan> oldDiwan = diwanRepository.findByName(name);
        return oldDiwan.orElse(null);
    }

    public DiwanTraduction addUpdateDiwanTraduction(DiwanTraductionQuery q) {
        DiwanTraduction toSave =  new DiwanTraduction( q.getDiwanTradId(), q.getName(), q.getCodeLangue());

        if(q.getDiwanId()!=null && q.getDiwanId()!=0){
            Optional<Diwan> oldDiwan= diwanRepository.findByDiwanId(q.getDiwanId());
            oldDiwan.ifPresent(toSave::setDiwan);
        }else {
            log.info("diwanID is not filled...");
            return null;
        }

        if( q.getName()!=null && q.getCodeLangue()!=null){
            Optional<DiwanTraduction> oldDiwanTraduction = diwanTraductionRepository.findByNameAndCodeLangue(q.getName(), q.getCodeLangue());
            oldDiwanTraduction.ifPresent(diwanTraduction -> toSave.setDiwanTradId(diwanTraduction.getDiwanTradId()));
        }

        return diwanTraductionRepository.save(toSave);

    }


}
