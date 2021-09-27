package edu.bbte.quiz.backend.dao.jdbc;

import edu.bbte.quiz.backend.dao.AbstractDaoFactory;
import edu.bbte.quiz.backend.dao.OwnerDao;
import edu.bbte.quiz.backend.dao.UsedCarDao;

public class JdbcDaoFactory extends AbstractDaoFactory {

    private static final UsedCarJdbcDao USEDCAR_JDBC_DAO = new UsedCarJdbcDao();
    private static final OwnerJdbcDao OWNER_JDBC_DAO = new OwnerJdbcDao();

    @Override
    public UsedCarDao getUsedCarDao() {
        return USEDCAR_JDBC_DAO;
    }

    @Override
    public OwnerDao getOwnerDao() {
        return OWNER_JDBC_DAO;
    }
}
