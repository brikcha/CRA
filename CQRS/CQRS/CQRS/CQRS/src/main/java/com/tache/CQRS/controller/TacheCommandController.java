package com.tache.CQRS.controller;

import com.tache.CQRS.command.service.ITacheCommandService;
import com.tache.CQRS.domain.TacheCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tache-command")
@CrossOrigin(origins = "*")
public class TacheCommandController {

    @Autowired
    ITacheCommandService tacheCommandServiceImp;

    // api de creation du tache
    @PostMapping("/create")
    public  ResponseEntity<?> createTache(@RequestBody TacheCommand t){
        //modifier la date de dérniere modification
        t.setCreationDate(new Date());
        if(this.tacheCommandServiceImp.createTache(t)==1){
            return new ResponseEntity<>( HttpStatus.CREATED);
        };
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //api de récupération de tous les taches  pour des fins de tests
    @GetMapping("all")
    public List<TacheCommand> getAll(){
        return this.tacheCommandServiceImp.getAll();
    }

    //api de suppression du tache
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTache( @PathVariable long id){

        try{
            this.tacheCommandServiceImp.deleteTache(id);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.CONFLICT);

        }


    }

    //api de modification du tache
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody TacheCommand t){
        //p.setLastModifiedDate(new Date());
        if(this.tacheCommandServiceImp.updateTache(t)==1){
            return new ResponseEntity<>(HttpStatus.CREATED);
        };
        return  new ResponseEntity<>(HttpStatus.CONFLICT);

    }
}
