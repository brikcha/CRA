package com.tache.CQRS.query.service;

import com.tache.CQRS.domain.TacheQuery;
import com.tache.CQRS.query.repo.TacheQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TacheQueryServiceImp implements ITacheQueryService{

    //injection des methode de TacheQueryRepository
    @Autowired
    private TacheQueryRepository tacheQueryRepository;

    //service de récupération d'une tache  by id
     @Override
    public TacheQuery findById(long id) {
        return tacheQueryRepository.findById(id).orElse(null);
    }

    //service de récupération des taches
     @Override
    public List<TacheQuery> getTaches() {
        return tacheQueryRepository.findAll();
    }

    //service de récupération d'une tache  par titre
    @Override
    public TacheQuery findByTitle(String s) {
        return tacheQueryRepository.findByTitle(s);
    }

    //service de récupération d'une tache  par  StartDate
    @Override
    public List<TacheQuery> findByStartDate(Date d) {
        return tacheQueryRepository.findByStartDate(d);
    }

    //service de récupération d'une tache par EndDate
    @Override
    public List<TacheQuery> findByEndDate(Date d) {
        return tacheQueryRepository.findByEndDate(d);
    }

    //service de récupération d'une tache par son créateur
    @Override
    public List<TacheQuery> findByCreatedBy(String user) {
        return tacheQueryRepository.findByCreatedBy(user);
    }
}
