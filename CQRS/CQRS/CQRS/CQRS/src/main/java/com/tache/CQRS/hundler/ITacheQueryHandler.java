package com.tache.CQRS.hundler;

import com.tache.CQRS.domain.TacheQuery;

public interface ITacheQueryHandler {
    public  void createTache(TacheQuery tacheQuery);
    public void updateTache(TacheQuery tacheQuery);
    public  void deleteTache(TacheQuery tacheQueryDto);
}
