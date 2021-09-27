package edu.bbte.pnim1858.springbackend.mapper;

import edu.bbte.pnim1858.springbackend.dto.incoming.OwnerCreationDto;
import edu.bbte.pnim1858.springbackend.dto.incoming.OwnerUpdateDto;
import edu.bbte.pnim1858.springbackend.dto.outgoing.OwnerDetailsDto;
import edu.bbte.pnim1858.springbackend.dto.outgoing.OwnerDto;
import edu.bbte.pnim1858.springbackend.model.Owner;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OwnerMapper {
    public abstract Owner createDtoToModel(OwnerCreationDto ownerCreationDto);

    public abstract OwnerDetailsDto modelToDetailsDto(Owner owner);

    @IterableMapping(elementTargetType =  Owner.class)
    public abstract List<OwnerDto> modelsToDtos(List<Owner> owners);

    public void updateDtoToModel(OwnerUpdateDto newOwner, Owner owner) {
        owner.setCity(newOwner.getCity());
        owner.setName(newOwner.getName());
    }

}
