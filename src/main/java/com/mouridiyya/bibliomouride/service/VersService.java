package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Chapitre;
import com.mouridiyya.bibliomouride.entity.Vers;
import com.mouridiyya.bibliomouride.entity.VersTraduction;
import com.mouridiyya.bibliomouride.model.VersQuery;
import com.mouridiyya.bibliomouride.model.VersTraductionQuery;
import com.mouridiyya.bibliomouride.repository.ChapitreRepository;
import com.mouridiyya.bibliomouride.repository.VersRepository;
import com.mouridiyya.bibliomouride.repository.VersTraductionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VersService {

    @Autowired
    private VersRepository versRepository;

    @Autowired
    private ChapitreRepository chapitreRepository;

    @Autowired
    private VersTraductionRepository versTraductionRepository;



    public List<Vers> getVers() {
        log.info("Connecting to DB...");
        return Lists.newArrayList(versRepository.findAll());
    }


    public Vers addUpdateVers(VersQuery q) {

        Vers toSave =  new Vers( q.getVersId(), q.getTypeVers(), q.getNumVers(), q.getRefVersNote(),
        q.getTexteVersAR1(), q.getTexteVersAR2(),q.getTexteVersAR3(),q.getTexteVersAR4());


        if(q.getChapitreId()!=null && q.getChapitreId()!=0){
            Optional<Chapitre> oldChapitre = chapitreRepository.findById(Optional.ofNullable(q.getChapitreId()).orElse(null));
                oldChapitre.ifPresent(toSave::setChapitre);
        }
        if(q.getVersId()!=null && q.getVersId()!=0){
            Optional<Vers> oldVers= versRepository.findByVersId(q.getVersId());
            oldVers.ifPresent(categorie -> toSave.setVersId(categorie.getVersId()));

        }

        return versRepository.save(toSave);
    }

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