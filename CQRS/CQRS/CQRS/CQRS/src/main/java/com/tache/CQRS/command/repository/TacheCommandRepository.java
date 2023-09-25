package com.tache.CQRS.command.repository;

import com.tache.CQRS.domain.TacheCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface TacheCommandRepository extends JpaRepository<TacheCommand, Long> {
    TacheCommand findByTitle(String s);
    List<TacheCommand> findByStartDate(Date d);
    List<TacheCommand> findByEndDate(Date d);
}
