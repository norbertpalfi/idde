package edu.bbte.quiz.backend.dao.jpa;

import edu.bbte.quiz.backend.dao.Dao;
import edu.bbte.quiz.backend.dao.DaoException;
import edu.bbte.quiz.backend.model.BaseEntity;

import javax.persistence.*;
import java.util.Collection;

public abstract class AbstractJpaDao<T extends BaseEntity> implements Dao<T> {

    protected EntityManager entityManager;
    protected Class<T> genericClass;

    public AbstractJpaDao(Class<T> genericClass) {
        this.genericClass = genericClass;
        entityManager = Persistence.createEntityManagerFactory("UsedCars").createEntityManager();

    }

    @Override
    public void create(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(entity);
            transaction.commit();
        } catch (PersistenceException e) {
            transaction.rollback();
            throw new DaoException("Transaction failed!", e);
        }
    }

    @Override
    public T select(Integer id) {
        return entityManager.find(genericClass,id);
    }

    @Override
    public Collection<T> selectAll() {
        TypedQuery<T> typedQuery = entityManager.createQuery("from " + genericClass.getSimpleName(), genericClass);
        return typedQuery.getResultList();
    }

    @Override
    public void update(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.merge(entity);
            transaction.commit();
        } catch (PersistenceException e) {
            transaction.rollback();
            throw new DaoException("Transaction failed!", e);
        }
    }

    @Override
    public void delete(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.remove(select(id));
            transaction.commit();
        } catch (PersistenceException e) {
            transaction.rollback();
            throw new DaoException("Transaction failed!", e);
        }

    }
}
