package com.tache.CQRS.command.service;

import com.tache.CQRS.domain.TacheCommand;
import java.util.List;

public interface ITacheCommandService {
    int createTache(TacheCommand tacheCommand) ;
    int updateTache(TacheCommand tacheCommand);
    void deleteTache(Long id );
    List<TacheCommand> getAll();
}
