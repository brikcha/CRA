package com.tache.CQRS.controller;

import com.tache.CQRS.domain.TacheQuery;
import com.tache.CQRS.query.repo.TacheQueryRepository;
import com.tache.CQRS.query.service.ITacheQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/tache-query")
@CrossOrigin("*")
public class TacheQueryController {
    @Autowired
    ITacheQueryService tacheQueryServiceImp;

    //api de récupération du tache par id
    @GetMapping("/tachebyid/{id}")
    public TacheQuery getTacheById(@PathVariable  long id){

        return this.tacheQueryServiceImp.findById(id);
    }

    //api de récupération de tous les taches
    @GetMapping("/all")
    public List<TacheQuery> getTaches(){

        return this.tacheQueryServiceImp.getTaches();
    }

    //api de récupération du tache par son créateur
    @GetMapping("/tache-CreatedBy/{username}")
    public List<TacheQuery> getTachesCreatedBy(@PathVariable("username") String username)
    {

        return  this.tacheQueryServiceImp.findByCreatedBy(username);
    }

    //api de récupération du tache son titre
    @GetMapping("/tachebyTitle/{title}")
    public TacheQuery getTacheTitle(@PathVariable String title){
        return this.tacheQueryServiceImp.findByTitle(title);
    }

    //api de récupération du tache par StartDate
    @GetMapping("/withStartDate/{startdate}")
    public List<TacheQuery> getTachewithStartDate(@PathVariable Date startdate){
        return this.tacheQueryServiceImp.findByStartDate(startdate);
    }

    //api de récupération du tache par EndDate
    @GetMapping("/withEndDate/{enddate}")
    public List<TacheQuery> getTachewithEndDate(@PathVariable Date enddate){
        return this.tacheQueryServiceImp.findByEndDate(enddate);
    }


}
