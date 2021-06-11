package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Chapitre;
import com.mouridiyya.bibliomouride.entity.Oeuvre;
import com.mouridiyya.bibliomouride.entity.Vers;
import com.mouridiyya.bibliomouride.entity.VersTraduction;
import com.mouridiyya.bibliomouride.model.VersQuery;
import com.mouridiyya.bibliomouride.model.VersTraductionQuery;
import com.mouridiyya.bibliomouride.repository.ChapitreRepository;
import com.mouridiyya.bibliomouride.repository.OeuvreRepository;
import com.mouridiyya.bibliomouride.repository.VersRepository;
import com.mouridiyya.bibliomouride.repository.VersTraductionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VersService {

    @Autowired
    private VersRepository versRepository;

    @Autowired
    private OeuvreRepository oeuvreRepository;

    @Autowired
    private ChapitreRepository chapitreRepository;

    @Autowired
    private VersTraductionRepository versTraductionRepository;


    @Cacheable(cacheNames="findAllVers")
    public List<Vers> getVers() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(versRepository.findAll());
    }
    
    
    @Cacheable(cacheNames="findVersForOeuvre")
    public Page<Vers> getVersForOeuvre(long oeuvreId, Integer pageNo, Integer pageSize, String sortBy) {    
    	Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    	Page<Vers> pagedResult = versRepository.findByOeuvreId(oeuvreId, paging);
    	
            return pagedResult;
        
    }

    
    @Caching(evict = {
            @CacheEvict(value = "findAllVers", allEntries = true),
            @CacheEvict(value = "findVersById", allEntries = true)})

    public Vers addUpdateVers(VersQuery q) {

        Vers toSave =  new Vers( q.getVersId(), q.getTypeVers(), q.getNumVers(), q.getRefVersNote(), q.getAudioVers(),
        q.getTexteVersAR1(), q.getTexteVersAR2(),q.getTexteVersAR3(),q.getTexteVersAR4());


        if(q.getChapitreId()!=null && q.getChapitreId()!=0){
            Optional<Chapitre> oldChapitre = chapitreRepository.findById(Optional.ofNullable(q.getChapitreId()).orElse(null));
                oldChapitre.ifPresent(toSave::setChapitre);
        }

        if(q.getOeuvreId()!=null && q.getOeuvreId()!=0){
            Optional<Oeuvre> oldOeuvre = oeuvreRepository.findById(Optional.ofNullable(q.getOeuvreId()).orElse(null));
            oldOeuvre.ifPresent(toSave::setOeuvre);
        }

        if(q.getVersId()!=null && q.getVersId()!=0){
            Optional<Vers> oldVers= versRepository.findByVersId(q.getVersId());
            oldVers.ifPresent(categorie -> toSave.setVersId(categorie.getVersId()));

        }

        return versRepository.save(toSave);
    }

    @Cacheable(cacheNames = "findVersById")
    public Vers get(long id) {
        log.info("Connecting to DB...");
        return versRepository.findById(id).get();
    }


    public void delete(long id) {
        versRepository.deleteById(id);
    }

    public Vers findByTexteVersAR1AndTexteVersAR2(String texteVersAR1, String texteVersAR2){
        Optional<Vers> oldVers = versRepository.findByTexteVersAR1AndTexteVersAR2(texteVersAR1, texteVersAR2);
        return oldVers.orElse(null);
    }

    public VersTraduction addUpdateVersTraduction(VersTraductionQuery q) {
        VersTraduction toSave =  new VersTraduction( q.getVersTradId(), q.getTexte(), q.getCodeLangue());

        if(q.getVersId()!=null && q.getVersId()!=0){
            Optional<Vers> oldVers = versRepository.findByVersId(q.getVersId());
            oldVers.ifPresent(toSave::setVers);
        }else {
            log.info("VersId is not filled...");
            return null;
        }

        if(q.getVersTradId()!=null && q.getVersTradId()!=0){
            Optional<VersTraduction> oldVersTraduction = versTraductionRepository.findByVersTradId(q.getVersTradId());
            oldVersTraduction.ifPresent(categorieTraduction -> toSave.setVersTradId(categorieTraduction.getVersTradId()));
        }

        return versTraductionRepository.save(toSave);

    }

}
