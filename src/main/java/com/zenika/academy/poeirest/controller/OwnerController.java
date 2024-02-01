package com.zenika.academy.poeirest.controller;

import com.zenika.academy.poeirest.controller.dto.OwnerDto;
import com.zenika.academy.poeirest.controller.dto.OwnerMapper;
import com.zenika.academy.poeirest.service.OwnerService;
import com.zenika.academy.poeirest.service.model.Owner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;
    private static final Logger logger = LoggerFactory.getLogger(PetClinicController.class);

    public OwnerController(OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService= ownerService;
        this.ownerMapper = ownerMapper;
    }

    @GetMapping
    public List<OwnerDto> findAll() {
        logger.info("Récupération de tous les propriétaires");
        return this.ownerService.findAll()
                .stream()
                .map(ownerMapper::ownerToOwnerDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<OwnerDto> addPetClinic(@RequestBody OwnerDto ownerDto) {
        try {
            logger.info(String.format("Add an owner %s", ownerDto.getName()));
            Owner ownerAdded = ownerService.add(ownerMapper.ownerDtoToOwner(ownerDto));
            OwnerDto ownerDtoAdded = ownerMapper.ownerToOwnerDto(ownerAdded);
            return ResponseEntity.status(HttpStatus.CREATED).body(ownerDtoAdded);
        } catch (NullPointerException nullPointerException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
//
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OwnerDto findById(@PathVariable("id") int id) {
        logger.info("Récupération de la clinique vétérinaire avec l'id " + id);
        Owner petClinicFound = this.ownerService.findById(id);
        if (Objects.nonNull(petClinicFound)) {
            return this.ownerMapper.ownerToOwnerDto(petClinicFound);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") int id) {
        logger.info("Suppression de la clinique vétérinaire avec l'id" + id);
        this.ownerService.deleteById(id);
    }
//
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable("id") int id, @RequestBody OwnerDto ownerDto) {
        try {
            logger.info("Mise à jour de la clinique vétérinaire avec l'id" + id);
            this.ownerService.updateById(id, ownerMapper.ownerDtoToOwner(ownerDto));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NullPointerException nullPointerException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
