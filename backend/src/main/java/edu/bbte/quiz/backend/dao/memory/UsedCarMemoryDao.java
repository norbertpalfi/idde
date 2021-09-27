package edu.bbte.quiz.backend.dao.memory;

import edu.bbte.quiz.backend.dao.DaoException;
import edu.bbte.quiz.backend.dao.UsedCarDao;
import edu.bbte.quiz.backend.model.UsedCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UsedCarMemoryDao implements UsedCarDao {

    private static final Map<Integer, UsedCar> QUIZ_MAP = new ConcurrentHashMap<>();
    private static final AtomicInteger ID_COUNTER = new AtomicInteger();
    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarMemoryDao.class);

    public UsedCarMemoryDao() {
        this.create(new UsedCar(10, 100,"1st", 1999));
        this.create(new UsedCar(9, 121,"2nd", 1960));
    }

    @Override
    public Collection<UsedCar> selectAll() {
        LOGGER.info("selecting all");
        return QUIZ_MAP.values();
    }

    @Override
    public UsedCar select(Integer id) {
        LOGGER.info("selecting {}", id);
        return QUIZ_MAP.get(id);
    }

    @Override
    public void delete(Integer id) {
        QUIZ_MAP.remove(id);
        LOGGER.info("deleted {}",id);
    }

    @Override
    public void create(UsedCar entity) {
        int id = ID_COUNTER.getAndIncrement();
        entity.setId(id);
        LOGGER.info("creating {}", id);
        QUIZ_MAP.put(id, entity);
    }

    @Override
    public void update(UsedCar entity) {
        QUIZ_MAP.replace(entity.getId(), entity);
    }

    @Override
    public Collection<UsedCar> selectByName(String name) {
        throw new DaoException("Not implemented");
    }

    @Override
    public UsedCar selectByOwnerName(String name) {
        throw new DaoException("Not implemented");
    }
}
