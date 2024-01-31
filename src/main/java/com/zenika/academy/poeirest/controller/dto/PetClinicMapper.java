package com.zenika.academy.poeirest.controller.dto;

import com.zenika.academy.poeirest.service.model.PetClinic;
import org.springframework.stereotype.Component;

@Component
public class PetClinicMapper {

    public PetClinic getPetClinicDtoToPetClinic(PetClinicDto from) {
        return new PetClinic(from.getId(), from.getName());
    }

    public PetClinicDto getPetClinicToPetClinicDto ( PetClinic from) {
        return new PetClinicDto(from.getId(), from.getName());
    }
}
