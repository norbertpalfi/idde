package edu.bbte.pnim1858.springbackend.mapper;

import edu.bbte.pnim1858.springbackend.dto.incoming.UsedCarCreationDto;
import edu.bbte.pnim1858.springbackend.dto.outgoing.UsedCarDto;
import edu.bbte.pnim1858.springbackend.model.UsedCar;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UsedCarMapper {
    public abstract UsedCar createDtoToModel(UsedCarCreationDto usedCarCreationDto);

    public abstract UsedCarDto modelToDto(UsedCar usedCar);

    @IterableMapping(elementTargetType =  UsedCar.class)
    public abstract List<UsedCarDto> modelsToDtos(List<UsedCar> cars);

}
