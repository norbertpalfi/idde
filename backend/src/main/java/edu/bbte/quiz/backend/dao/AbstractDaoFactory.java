package edu.bbte.quiz.backend.dao;

import edu.bbte.quiz.backend.config.ConfigurationFactory;
import edu.bbte.quiz.backend.dao.jdbc.JdbcDaoFactory;
import edu.bbte.quiz.backend.dao.jpa.JpaDaoFactory;
import edu.bbte.quiz.backend.dao.memory.MemoryDaoFactory;

public abstract class AbstractDaoFactory {

    private static AbstractDaoFactory instance;

    public abstract UsedCarDao getUsedCarDao();

    public abstract OwnerDao getOwnerDao();

    public static synchronized AbstractDaoFactory getInstance() {
        if (instance == null) {
            String daoType = ConfigurationFactory.getMainConfiguration().getDaoType();

            if ("mem".equals(daoType)) {
                instance = new MemoryDaoFactory();
            } else if ("jdbc".equals(daoType)) {
                instance = new JdbcDaoFactory();
            } else if ("jpa".equals(daoType)) {
                instance = new JpaDaoFactory();
            } else {
                throw new DaoException("Could not read config file.".concat(daoType));
            }
        }
        return instance;
    }

}
