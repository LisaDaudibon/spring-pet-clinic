package com.zenika.academy.poeirest.controller;

import com.zenika.academy.poeirest.controller.dto.PetClinicDto;
import com.zenika.academy.poeirest.controller.dto.PetClinicMapper;
import com.zenika.academy.poeirest.service.PetClinicService;
import com.zenika.academy.poeirest.service.model.PetClinic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pet-clinics")
public class PetClinicController {

    private final PetClinicService petClinicService;
    private final PetClinicMapper petClinicMapper;
    private static final Logger logger = LoggerFactory.getLogger(PetClinicController.class);

    public PetClinicController(PetClinicService petClinicService, PetClinicMapper petClinicMapper) {
        this.petClinicService = petClinicService;
        this.petClinicMapper = petClinicMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PetClinicDto> findAll() {
        logger.info("Récupération de toutes les cliniques vétérinaire");
        return this.petClinicService.findAll()
                .stream()
                .map(petClinicMapper::getPetClinicToPetClinicDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<PetClinicDto> addPetClinic(@RequestBody PetClinicDto petClinicDto) {
        try {
            logger.info("Add a pet Clinic");
            PetClinic petClinicAdded = petClinicService.add(petClinicMapper.getPetClinicDtoToPetClinic(petClinicDto));
            PetClinicDto petClinicDtoAdded = petClinicMapper.getPetClinicToPetClinicDto(petClinicAdded);
            return ResponseEntity.status(HttpStatus.CREATED).body(petClinicDtoAdded);
        } catch (NullPointerException nullPointerException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PetClinicDto findById(@PathVariable("id") int id) {
        logger.info("Récupération de la clinique vétérinaire avec l'id " + id);
        PetClinic petClinicFound = this.petClinicService.getById(id);
        if (Objects.nonNull(petClinicFound)) {
            return this.petClinicMapper.getPetClinicToPetClinicDto(petClinicFound);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") int id) {
        logger.info("Suppression de la clinique vétérinaire avec l'id" + id);
        this.petClinicService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable("id") int id, @RequestBody PetClinicDto petClinicDto) {
        try {
            logger.info("Mise à jour de la clinique vétérinaire avec l'id" + id);
            this.petClinicService.updateById(id, petClinicMapper.getPetClinicDtoToPetClinic(petClinicDto));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NullPointerException nullPointerException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
