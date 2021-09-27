package edu.bbte.quiz.backend.dao.jpa;

import edu.bbte.quiz.backend.dao.UsedCarDao;
import edu.bbte.quiz.backend.model.UsedCar;

import javax.persistence.TypedQuery;
import java.util.Collection;

public class UsedCarJpaDao extends AbstractJpaDao<UsedCar> implements UsedCarDao {
    public UsedCarJpaDao() {
        super(UsedCar.class);
    }

    @Override
    public Collection<UsedCar> selectByName(String search) {
        final TypedQuery<UsedCar> selectByName = entityManager.createNamedQuery(
                UsedCar.USED_CAR_SELECT_BY_NAME, UsedCar.class);
        selectByName.setParameter("carModel", "%" + search + "%");
        return selectByName.getResultList();
    }

    @Override
    public UsedCar selectByOwnerName(String ownerName) {
        for (UsedCar car:selectAll()) {
            if (car.getOwner().getName().equals(ownerName)) {
                return car;
            }
        }
        return null;
    }
}
