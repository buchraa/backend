package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.Diwan;
import com.mouridiyya.bibliomouride.model.DiwanQuery;
import com.mouridiyya.bibliomouride.repository.DiwanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiwanService {

    @Autowired
    private DiwanRepository diwanRepository;

    public List<Diwan> getDiwans() {
        return Lists.newArrayList(diwanRepository.findAll());
    }

    public Diwan addUpdateDiwan(DiwanQuery q) {
        Diwan toSave =  new Diwan( q.getRefDiwan(),  q.getTitreDiwanAR(),  q.getTttreDiwanFR());
        if(Optional.ofNullable(q.getRefDiwan()).orElse(null)!=null && q.getRefDiwan() !=0){
            Optional<Diwan> oldDiwan = diwanRepository.findById(Optional.ofNullable(q.getRefDiwan()).orElse(null));
            if(oldDiwan.isPresent()){
                toSave.setRefDiwan(oldDiwan.get().getRefDiwan());
            }
        }
        return diwanRepository.save(toSave);
    }

}
