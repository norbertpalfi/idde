package edu.bbte.quiz.backend.model;

import javax.persistence.*;

@Entity
@NamedQuery(name = UsedCar.USED_CAR_SELECT_BY_NAME, query = "from UsedCar where carModel like :carModel")
public class UsedCar extends BaseEntity {
    public static final String USED_CAR_SELECT_BY_NAME = "UsedCar.selectByName";
    private Integer kilometers;
    private Integer horsePower;
    @Column(length = 512)
    private String carModel;
    private Integer startDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ownerId", insertable = false, updatable = false)
    private Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public UsedCar(Integer kilometers, Integer horsePower, String carModel, Integer startDate) {
        super();
        this.kilometers = kilometers;
        this.horsePower = horsePower;
        this.carModel = carModel;
        this.startDate = startDate;
    }

    public UsedCar() {
        super();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Cars{");
        sb.append("kilometers=").append(kilometers);
        sb.append(", horsepower=").append(horsePower);
        sb.append(", model='").append(carModel).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
