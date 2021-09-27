package edu.bbte.pnim1858.springbackend.repo;

import edu.bbte.pnim1858.springbackend.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerSpringDataDao extends JpaRepository<Owner, Integer> {
    @Query("from Owner o where o.name like :name and o.deleted = false")
    List<Owner> findAllByName(@Param("name") String name);

    @Query("from Owner o where o.deleted = false")
    List<Owner> findAllOK();

    @Query("from Owner o where o.id = :id and o.deleted = false")
    Optional<Owner> findByIdOk(@Param("id") Integer id);

    @Query("from Owner where city like :city")
    List<Owner> findAllByCity(@Param("city") String city);

    @Query("from Owner o where (from UsedCar u where u.id = :id) MEMBER OF o.cars")
    Optional<Owner> findOwnerWithCar(@Param("id") Integer id);

}
