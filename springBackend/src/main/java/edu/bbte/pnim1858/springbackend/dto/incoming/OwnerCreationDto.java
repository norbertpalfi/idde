package edu.bbte.pnim1858.springbackend.dto.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class OwnerCreationDto implements Serializable {
    @NotEmpty
    @Size(max = 256)
    @Pattern(regexp = "[a-zA-Z0-9 ]+")
    private String city;
    @NotEmpty
    @Size(max = 256)
    @Pattern(regexp = "[a-zA-Z0-9 ]+")
    private String name;
}