package edu.bbte.quiz.backend.dao;

import edu.bbte.quiz.backend.model.Owner;

import java.util.Collection;

public interface OwnerDao extends Dao<Owner> {
    Collection<Owner> selectByName(String name);

    Owner selectByCarModel(String carModel);
}
