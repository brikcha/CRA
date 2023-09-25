package com.tache.CQRS.query.service;

import com.tache.CQRS.domain.TacheQuery;
//import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;



public interface ITacheQueryService {
    TacheQuery findById(long Id);

    List<TacheQuery> getTaches();
    TacheQuery findByTitle(String s);
    List<TacheQuery>  findByStartDate(Date d);
    List<TacheQuery> findByEndDate(Date d);
    List<TacheQuery> findByCreatedBy(String user);
}
