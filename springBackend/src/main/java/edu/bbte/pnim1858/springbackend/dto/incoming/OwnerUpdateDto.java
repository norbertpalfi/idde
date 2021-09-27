package edu.bbte.pnim1858.springbackend.dto.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class OwnerUpdateDto implements Serializable {
    @Size(max = 256)
    @Pattern(regexp = "[a-zA-Z ]+")
    private String name;
    @Size(max = 256)
    @Pattern(regexp = "[a-zA-Z ]+")
    private String city;
}
