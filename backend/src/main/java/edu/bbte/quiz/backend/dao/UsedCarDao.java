package edu.bbte.quiz.backend.dao;

import edu.bbte.quiz.backend.model.UsedCar;

import java.util.Collection;

public interface UsedCarDao extends Dao<UsedCar> {

    Collection<UsedCar> selectByName(String name);

    UsedCar selectByOwnerName(String name);
}
