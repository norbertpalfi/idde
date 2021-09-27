package edu.bbte.pnim1858.springbackend.dto.outgoing;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UsedCarDto implements Serializable {
    private Integer id;
    private Integer kilometers;
    private Integer horsePower;
    private String carModel;
    private Integer ownerId;
    private Integer startDate;
}
