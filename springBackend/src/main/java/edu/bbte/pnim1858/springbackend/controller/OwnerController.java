package edu.bbte.pnim1858.springbackend.controller;

import edu.bbte.pnim1858.springbackend.controller.exception.NotFoundSpringException;
import edu.bbte.pnim1858.springbackend.dto.incoming.OwnerCreationDto;
import edu.bbte.pnim1858.springbackend.dto.incoming.OwnerUpdateDto;
import edu.bbte.pnim1858.springbackend.dto.outgoing.OwnerDetailsDto;
import edu.bbte.pnim1858.springbackend.dto.outgoing.OwnerDto;
import edu.bbte.pnim1858.springbackend.mapper.OwnerMapper;
import edu.bbte.pnim1858.springbackend.model.Owner;
import edu.bbte.pnim1858.springbackend.repo.OwnerSpringDataDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    OwnerSpringDataDao ownerSpringDataDao;
    @Autowired
    OwnerMapper ownerMapper;

    @GetMapping("/deleted")
    @ResponseBody
    public List<OwnerDto> findAllOwnersDeleted() {
        return ownerMapper.modelsToDtos(ownerSpringDataDao.findAll());
    }

    @GetMapping
    @ResponseBody
    public List<OwnerDto> findAllOwners(@RequestParam(value = "name", required = false) String name) {
        if (name == null) {
            return ownerMapper.modelsToDtos(ownerSpringDataDao.findAllOK());
        } else {
            return ownerMapper.modelsToDtos(ownerSpringDataDao.findAllByName(name));
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public OwnerDetailsDto findByOwnerID(@PathVariable("id") Integer id) {
        return ownerMapper.modelToDetailsDto(ownerSpringDataDao.findByIdOk(id).orElseThrow(NotFoundSpringException::new));
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerDetailsDto createOwner(@RequestBody @Valid OwnerCreationDto owner) {
        return ownerMapper.modelToDetailsDto(ownerSpringDataDao.save(ownerMapper.createDtoToModel(owner)));
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public OwnerDetailsDto updateOwner(@RequestBody @Valid OwnerUpdateDto newOwner, @PathVariable("id") Integer id) {
        Owner owner = ownerSpringDataDao.findByIdOk(id).orElseThrow(NotFoundSpringException::new);
        ownerMapper.updateDtoToModel(newOwner, owner);
        return ownerMapper.modelToDetailsDto(ownerSpringDataDao.save(owner));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String deleteOwner(@PathVariable("id") Integer id) {
        Owner owner = ownerSpringDataDao.findByIdOk(id).orElseThrow(NotFoundSpringException::new);

        owner.setDeleted(true);
        ownerSpringDataDao.save(owner);
        return "Owner successfully deleted";


    }

    //I don't recall if this is REST or the one in the UsedCar Controller. that one is /cars/{id}/owner.
    //either way, the wrong one will be removed
    @GetMapping("/car/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public OwnerDetailsDto findByCarId(@PathVariable("id") Integer id) {
        return ownerMapper.modelToDetailsDto(ownerSpringDataDao.findOwnerWithCar(id).orElseThrow(NotFoundSpringException::new));
    }
}
