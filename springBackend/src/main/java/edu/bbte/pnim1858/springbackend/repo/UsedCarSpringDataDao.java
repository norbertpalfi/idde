package edu.bbte.pnim1858.springbackend.repo;

import edu.bbte.pnim1858.springbackend.model.Owner;
import edu.bbte.pnim1858.springbackend.model.UsedCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsedCarSpringDataDao extends JpaRepository<UsedCar, Integer> {
    @Query("from UsedCar where carModel like :name")
    List<UsedCar> findAllByName(@Param("name") String name);

    @Query("from UsedCar where horsePower>:horsePower")
    List<UsedCar> findAllWithGreaterHorsePowerThan(@Param("horsePower") Integer horsePower);

    @Query("from Owner AS O JOIN UsedCar as U ON O.id = U.ownerId WHERE O.name like :name")
    List<UsedCar> findCarsByOwnerName(@Param("name") String name);

    @Query("from Owner o where (from UsedCar u where u.id = :id) MEMBER OF o.cars")
    Owner findOwnerWithCar(@Param("id") Integer id);
}