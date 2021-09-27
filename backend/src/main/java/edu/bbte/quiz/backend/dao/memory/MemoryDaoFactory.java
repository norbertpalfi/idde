package edu.bbte.quiz.backend.dao.memory;

import edu.bbte.quiz.backend.dao.AbstractDaoFactory;
import edu.bbte.quiz.backend.dao.OwnerDao;
import edu.bbte.quiz.backend.dao.UsedCarDao;

public class MemoryDaoFactory extends AbstractDaoFactory {

    public static final UsedCarMemoryDao QUIZ_MEMORY_DAO = new UsedCarMemoryDao();
    public static final OwnerMemoryDao OWNER_MEMORY_DAO = new OwnerMemoryDao();

    @Override
    public UsedCarDao getUsedCarDao() {
        return QUIZ_MEMORY_DAO;
    }

    @Override
    public OwnerDao getOwnerDao() {
        return OWNER_MEMORY_DAO;
    }

}
