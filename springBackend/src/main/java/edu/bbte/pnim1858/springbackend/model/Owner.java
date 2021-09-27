package edu.bbte.pnim1858.springbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Owner extends BaseEntity {
    private String name;
    private String city;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    @JsonIgnore
    private Collection<UsedCar> cars = new ArrayList<>();

    public Owner() {
        super();
    }

    public Owner(String name, String city, Collection<UsedCar> cars) {
        super();
        this.name = name;
        this.city = city;
        this.cars = cars;
    }
}
