package edu.bbte.quiz.backend.dao.memory;

import edu.bbte.quiz.backend.dao.DaoException;
import edu.bbte.quiz.backend.dao.OwnerDao;
import edu.bbte.quiz.backend.model.Owner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OwnerMemoryDao implements OwnerDao {

    private static final Map<Integer, Owner> OWNER_MAP = new ConcurrentHashMap<>();
    private static final AtomicInteger ID_COUNTER = new AtomicInteger();
    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarMemoryDao.class);

    public OwnerMemoryDao() {
        this.create(new Owner("Maricescu Sanu","Cluj-Napoca"));
        this.create(new Owner("Ela Guran","Dambovita"));
    }

    @Override
    public Collection<Owner> selectAll() {
        LOGGER.info("selecting all");
        return OWNER_MAP.values();
    }

    @Override
    public Owner select(Integer id) {
        LOGGER.info("selecting {}", id);
        return OWNER_MAP.get(id);
    }

    @Override
    public void delete(Integer id) {
        OWNER_MAP.remove(id);
        LOGGER.info("deleted {}",id);
    }

    @Override
    public void create(Owner entity) {
        int id = ID_COUNTER.getAndIncrement();
        entity.setId(id);
        LOGGER.info("creating {}", id);
        OWNER_MAP.put(id, entity);
    }

    @Override
    public void update(Owner entity) {
        entity.setId(entity.getId());
        OWNER_MAP.replace(entity.getId(), entity);
    }

    @Override
    public Collection<Owner> selectByName(String name) {
        throw new DaoException("Not implemented");
    }

    @Override
    public Owner selectByCarModel(String carModel) {
        throw new DaoException("Not implemented");
    }
}
