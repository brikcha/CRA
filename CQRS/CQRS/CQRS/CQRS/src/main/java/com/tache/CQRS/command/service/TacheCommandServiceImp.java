package com.tache.CQRS.command.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tache.CQRS.command.repository.TacheCommandRepository;
import com.tache.CQRS.domain.TacheCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TacheCommandServiceImp implements ITacheCommandService{
    //injection des methode de TacheCommandRepository
    @Autowired
    TacheCommandRepository tacheCommandRepository;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    private void raiseEventToQueryProject(TacheCommand t, String topic){
        try{
            String value = OBJECT_MAPPER.writeValueAsString(t);
            this.kafkaTemplate.send(topic,value);
            System.out.println("sended");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //service de creation du tache
    @Override
    public int createTache(TacheCommand tacheCommand) {
        log.info("TacheCommand"+tacheCommand);
        //récupération du tache crée
        TacheCommand t= tacheCommandRepository.save(tacheCommand);
        //appel de la méthode qui va lever un evenment pour envoiyer un topic à kafka pour la creation du meme projet dans la base mongo
        this.raiseEventToQueryProject(t,"tache-event-create");
        return 1;
    }

    //service de modification du tache
     @Override
    public int updateTache( TacheCommand tacheCommand) {
        //verrification de la présence du tache à modifier, si il est présent on le modifie
        this.tacheCommandRepository.findById(tacheCommand.getId()).ifPresent(tacheCommand1 -> {
            tacheCommand1.setId(tacheCommand.getId());
            tacheCommand1.setLastModifiedDate(new Date());
            tacheCommand1.setTitle(tacheCommand.getTitle());
            tacheCommand1.setTypeT(tacheCommand.getTypeT());
            tacheCommand1.setStartDate(tacheCommand.getStartDate());
            tacheCommand1 .setCreationDate(tacheCommand.getCreationDate());
            tacheCommand1.setDescription(tacheCommand.getDescription());
            tacheCommand1.setEndDate(tacheCommand.getEndDate());
            tacheCommand1.setMembers(tacheCommand.getMembers());
            tacheCommand1.setCreatedBy(tacheCommand.getCreatedBy());
            tacheCommandRepository.save(tacheCommand1);
            //appel de la méthode qui va lever un evenment pour envoiyer un topic à kafka pour la modification du meme tache dans la base mongo
            this.raiseEventToQueryProject(tacheCommand1,"tache-event-update");
        });
        return 1;
    }

    //service de suppression du tache
     @Override
    public void deleteTache(Long id) {
        TacheCommand tacheCommand= new TacheCommand();
        tacheCommand.setId(id);
        tacheCommandRepository.deleteById(id);
        //appel de la méthode qui va lever un evenment pour envoiyer un topic à kafka pour la suppression du meme tache dans la base mongo
        this.raiseEventToQueryProject(tacheCommand,"tache-event-delete");
    }


    //service de récupération des tache juste pour des raisons de test
     @Override
    public List<TacheCommand> getAll() {
        return this.tacheCommandRepository.findAll();
    }
}
