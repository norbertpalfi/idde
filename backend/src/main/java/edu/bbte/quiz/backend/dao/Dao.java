package edu.bbte.quiz.backend.dao;

import edu.bbte.quiz.backend.model.BaseEntity;

import java.util.Collection;

public interface Dao<T extends BaseEntity> {

    Collection<T> selectAll();

    T select(Integer id);

    void create(T entity);

    void delete(Integer id);

    void update(T entity);
}
