package edu.bbte.quiz.backend.dao.jpa;

import edu.bbte.quiz.backend.dao.OwnerDao;
import edu.bbte.quiz.backend.model.Owner;
import edu.bbte.quiz.backend.model.UsedCar;

import java.util.Collection;

public class OwnerJpaDao extends AbstractJpaDao<Owner> implements OwnerDao {
    public OwnerJpaDao() {
        super(Owner.class);
    }

    @Override
    public Collection<Owner> selectByName(String search) {
        return null;
    }

    @Override
    public Owner selectByCarModel(String carModel) {
        for (Owner owner:selectAll()) {
            for (UsedCar car: owner.getCars()) {
                if (carModel.equals(car.getCarModel())) {
                    return owner;
                }
            }
        }
        return null;
    }
}
