package com.zenika.academy.poeirest.controller;

import com.zenika.academy.poeirest.controller.dto.PetClinicDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PetClinicController {

    List<PetClinicDto> petClinicDtoList = new ArrayList<>();

    @GetMapping("/pet-clinics")
    public List<PetClinicDto> allClinics() {
        return petClinicDtoList;
    }

    @PostMapping("/pet-clinics")
    public void addPetClinic(@RequestBody PetClinicDto petClinicDto) {
        petClinicDtoList.add(petClinicDto);
    }

}
