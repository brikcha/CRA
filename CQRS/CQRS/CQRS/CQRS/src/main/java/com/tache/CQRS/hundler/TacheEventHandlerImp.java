package com.tache.CQRS.hundler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tache.CQRS.domain.TacheQuery;
import com.tache.CQRS.query.repo.TacheQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TacheEventHandlerImp implements ITacheQueryHandler{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private TacheQueryRepository tacheQueryRepository;

    @Override
    public void createTache(TacheQuery tacheQuery) {
        tacheQueryRepository.save(tacheQuery);
    }

    @Override
    public void updateTache(TacheQuery tacheQuery) {
        tacheQueryRepository.save(tacheQuery);
    }

    @Override
    public void deleteTache(TacheQuery tacheQuery) {
        tacheQueryRepository.deleteById(tacheQuery.getId());
    }

    //lister on the topic sended when creating one tache in mysql database to create in mongo database
    @KafkaListener(topics = "tache-event-create")
    public void consumeCreate(String userStr) {
        System.out.println("in");
        try {
            TacheQuery tacheQuery = OBJECT_MAPPER.readValue(userStr,TacheQuery.class);
            log.info(userStr);
            this.createTache(tacheQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //lister on the topic sended when updating one tache in mysql database to update in mongo database
    @KafkaListener(topics = "tache-event-update")
    public void consumeUpdate(String userStr) {
        try {
            TacheQuery tacheQuery = OBJECT_MAPPER.readValue(userStr, TacheQuery.class);
            this.updateTache(tacheQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //lister on the topic sended when deleting one tache in mysql database to delete in mongo database
    @KafkaListener(topics = "tache-event-delete")
    public void consumeDelete(String userStr) {
        try {
            //System.out.println(purchaseOrderStr);
                TacheQuery tacheQuery = OBJECT_MAPPER.readValue(userStr, TacheQuery.class);
            this.deleteTache(tacheQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
