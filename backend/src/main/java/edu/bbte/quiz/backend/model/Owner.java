package edu.bbte.quiz.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NamedQuery(name = Owner.OWNER_SELECT_BY_NAME, query = "from Owner where name like :name")
public class Owner extends BaseEntity {
    public static final String OWNER_SELECT_BY_NAME = "Owner.selectByName";
    @JoinColumn(name = "ownerId")
    @Column(length = 512)
    private String name;
    @Column(length = 512)
    private String city;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<UsedCar> cars = new ArrayList<>();

    public Owner() {
        super();
    }

    public Owner(String name, String city) {
        super();
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Collection<UsedCar> getCars() {
        return cars;
    }

    public void setCars(Collection<UsedCar> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Owner{");
        sb.append("name=").append(name);
        sb.append(", city=").append(city);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
