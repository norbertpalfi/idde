package edu.bbte.pnim1858.springbackend.dto.outgoing;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OwnerDto implements Serializable {
    private Integer id;
    private String name;
    private String city;
    private boolean deleted;
}
