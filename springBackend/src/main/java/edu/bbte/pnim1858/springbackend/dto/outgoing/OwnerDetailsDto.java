package edu.bbte.pnim1858.springbackend.dto.outgoing;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OwnerDetailsDto extends OwnerDto {
    List<UsedCarDto> cars;
}
