package edu.bbte.pnim1858.springbackend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class UsedCar extends BaseEntity {
    private Integer kilometers;
    private Integer horsePower;
    @Column(length = 512)
    private String carModel;
    @JoinColumn(name = "ownerId")
    private Integer ownerId;
    private Integer startDate;

    public UsedCar(Integer kilometers, Integer horsePower, String carModel, Integer ownerId, Integer startDate) {
        super();
        this.ownerId = ownerId;
        this.kilometers = kilometers;
        this.horsePower = horsePower;
        this.carModel = carModel;
        this.startDate = startDate;
    }

    public UsedCar() {
        super();
    }
}
