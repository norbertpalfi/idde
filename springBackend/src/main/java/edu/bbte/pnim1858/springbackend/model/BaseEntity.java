package edu.bbte.pnim1858.springbackend.model;

import jdk.jfr.BooleanFlag;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @BooleanFlag
    protected boolean deleted;

    public BaseEntity() {
        this.deleted = false;
    }

    public BaseEntity(Integer id) {
        this.id = id;
        this.deleted = false;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
