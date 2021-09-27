package edu.bbte.pnim1858.springbackend.controller;

import edu.bbte.pnim1858.springbackend.controller.exception.NotFoundSpringException;
import edu.bbte.pnim1858.springbackend.dto.incoming.UsedCarCreationDto;
import edu.bbte.pnim1858.springbackend.dto.outgoing.OwnerDetailsDto;
import edu.bbte.pnim1858.springbackend.dto.outgoing.UsedCarDto;
import edu.bbte.pnim1858.springbackend.mapper.OwnerMapper;
import edu.bbte.pnim1858.springbackend.mapper.UsedCarMapper;
import edu.bbte.pnim1858.springbackend.repo.UsedCarSpringDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class UsedCarController {
    @Autowired
    UsedCarSpringDataDao usedCarSpringDataDao;
    @Autowired
    UsedCarMapper usedCarMapper;
    @Autowired
    OwnerMapper ownerMapper;

    @GetMapping
    @ResponseBody
    public List<UsedCarDto> findAllUsedCars(@RequestParam(value = "name", required = false) String name) {
        if (name == null) {
            return usedCarMapper.modelsToDtos(usedCarSpringDataDao.findAll());
        } else {
            return usedCarMapper.modelsToDtos(usedCarSpringDataDao.findAllByName(name));
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UsedCarDto findByCarID(@PathVariable("id") Integer id) {
        return usedCarMapper.modelToDto(usedCarSpringDataDao.findById(id).orElseThrow(NotFoundSpringException::new));
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public UsedCarDto createCar(@RequestBody UsedCarCreationDto usedCar) {
        return usedCarMapper.modelToDto(usedCarSpringDataDao.save(usedCarMapper.createDtoToModel(usedCar)));
    }

    @GetMapping("/{id}/owner")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public OwnerDetailsDto findByCarId(@PathVariable("id") Integer id) {
        return ownerMapper.modelToDetailsDto(usedCarSpringDataDao.findOwnerWithCar(id));
    }
}
