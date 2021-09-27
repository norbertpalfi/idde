package edu.bbte.pnim1858.springbackend.dto.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UsedCarCreationDto implements Serializable {
    @Positive
    @NotNull
    private Integer kilometers;
    @Positive
    @NotNull
    private Integer horsePower;
    @Positive
    @NotNull
    private Integer ownerId;
    @NotEmpty
    @Size(max = 256)
    @Pattern(regexp = "[a-zA-Z0-9 ]+")
    private String carModel;
    @Positive
    @NotNull
    @Max(2021)
    private Integer startDate;
}
