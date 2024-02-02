package com.zenika.academy.poeirest.controller;

import com.zenika.academy.poeirest.controller.dto.OwnerDto;
import com.zenika.academy.poeirest.controller.dto.OwnerMapper;
import com.zenika.academy.poeirest.controller.dto.PetClinicDto;
import com.zenika.academy.poeirest.controller.dto.PetClinicMapper;
import com.zenika.academy.poeirest.service.OwnerService;
import com.zenika.academy.poeirest.service.PetClinicService;
import com.zenika.academy.poeirest.service.model.Owner;
import com.zenika.academy.poeirest.service.model.PetClinic;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pet-clinics")
public class PetClinicController {

    private final PetClinicService petClinicService;
    private final PetClinicMapper petClinicMapper;
    private final OwnerMapper ownerMapper;
    private final OwnerService ownerService;
    private static final Logger logger = LoggerFactory.getLogger(PetClinicController.class);

    public PetClinicController(PetClinicService petClinicService, PetClinicMapper petClinicMapper, OwnerMapper ownerMapper, OwnerService ownerService) {
        this.petClinicService = petClinicService;
        this.petClinicMapper = petClinicMapper;
        this.ownerMapper = ownerMapper;
        this.ownerService = ownerService;
    }

    private List<OwnerDto> findAllOwnerDtosByPetClinicId(int searchedPetClinicId) {
        List<Owner> petClinicOwners = this.ownerService.findAllOwnersByPetClinic(searchedPetClinicId);
        return petClinicOwners.stream().map(this.ownerMapper::ownerToOwnerDto).toList();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PetClinicDto> findAll(@RequestParam(required = false) String name) {
        logger.info("Récupération de toutes les cliniques vétérinaire");
        List<PetClinic> petClinics = this.petClinicService.findAll();
        List<PetClinicDto> petClinicDtos = new ArrayList<>();

        for (PetClinic petClinic : petClinics) {
            PetClinicDto petClinicDto = petClinicMapper.getPetClinicToPetClinicDto(petClinic);
            petClinicDto.setOwners(findAllOwnerDtosByPetClinicId(petClinicDto.getId()));
            petClinicDtos.add(petClinicDto);
        }

        if (Objects.nonNull(name)) {
           return petClinicDtos.stream()
                    .filter(petClinicDto -> petClinicDto.getName().contains(name))
                    .toList();
        }

        return petClinicDtos;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PetClinicDto findById(@PathVariable("id") int id) {
        logger.info("Récupération de la clinique vétérinaire avec l'id " + id);
        PetClinic petClinicFound = this.petClinicService.getById(id);

        if (Objects.nonNull(petClinicFound)) {
            PetClinicDto petClinicDto= this.petClinicMapper.getPetClinicToPetClinicDto(petClinicFound);
            List<Owner> petClinicOwners = this.ownerService.findAllOwnersByPetClinic(petClinicDto.getId());
            petClinicDto.setOwners(petClinicOwners.stream().map(this.ownerMapper::ownerToOwnerDto).toList());
            return petClinicDto;
        }

        return null;
    }

    @PostMapping
    public ResponseEntity<PetClinicDto> addPetClinic(@RequestBody @Valid PetClinicDto newPetClinicDto) {
        try {
            logger.info("Add a pet Clinic");
            PetClinic petClinicAdded = petClinicService.add(petClinicMapper.getPetClinicDtoToPetClinic(newPetClinicDto));
            PetClinicDto petClinicDto = petClinicMapper.getPetClinicToPetClinicDto(petClinicAdded);
            petClinicDto.setOwners(findAllOwnerDtosByPetClinicId(petClinicDto.getId()));
            return ResponseEntity.status(HttpStatus.CREATED).body(petClinicDto);
        } catch (NullPointerException nullPointerException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") int id) {
        logger.info("Suppression de la clinique vétérinaire avec l'id" + id);
        ownerService.deleteAllByPetClinicId(id);
        this.petClinicService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable("id") int id, @RequestBody PetClinicDto updatedPetClinicDto) {
        try {
            logger.info("Mise à jour de la clinique vétérinaire avec l'id" + id);
            PetClinic petClinicUpdated = petClinicService.updateById(id, petClinicMapper.getPetClinicDtoToPetClinic(updatedPetClinicDto));
            PetClinicDto petClinicDto = petClinicMapper.getPetClinicToPetClinicDto(petClinicUpdated);
            petClinicDto.setOwners(findAllOwnerDtosByPetClinicId(petClinicDto.getId()));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
