package com.my.app.core.domain;

import com.my.app.core.domain.type.Value;

import java.util.List;

public interface DataRepository<T extends Entity<ID>, ID extends EntityId<?>> {

     ID save(T entity);

    boolean deleteById(ID id);

    List<T> findAll();

    T findById(ID id);

    ID getLastId();

    ID getNextId();

}
