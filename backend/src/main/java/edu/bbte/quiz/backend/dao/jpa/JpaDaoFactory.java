package edu.bbte.quiz.backend.dao.jpa;

import edu.bbte.quiz.backend.dao.AbstractDaoFactory;
import edu.bbte.quiz.backend.dao.OwnerDao;
import edu.bbte.quiz.backend.dao.UsedCarDao;

public class JpaDaoFactory extends AbstractDaoFactory {

    public static final UsedCarJpaDao USEDCAR_JPA_DAO = new UsedCarJpaDao();
    public static final OwnerJpaDao OWNER_JPA_DAO = new OwnerJpaDao();

    @Override
    public UsedCarDao getUsedCarDao() {
        return USEDCAR_JPA_DAO;
    }

    @Override
    public OwnerDao getOwnerDao() {
        return OWNER_JPA_DAO;
    }
}
