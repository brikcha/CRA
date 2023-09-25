package com.tache.CQRS.query.repo;

import com.tache.CQRS.domain.TacheQuery;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;


@Repository
public interface TacheQueryRepository extends MongoRepository<TacheQuery, Long>{
   // @Query("{ 'title': ?0 }")
    TacheQuery findByTitle(String s);
   // @Query("{ 'startdate': ?0 }")
    List<TacheQuery> findByStartDate(Date d);
   // @Query("{ 'enddate': ?0 }")
    List<TacheQuery> findByEndDate(Date d);
   // @Query("{ 'createdby': ?0 }")
    List<TacheQuery> findByCreatedBy(String user);
}
