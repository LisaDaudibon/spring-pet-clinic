package com.zenika.academy.poeirest.controller.dto;

import com.zenika.academy.poeirest.service.model.Owner;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    public Owner ownerDtoToOwner (OwnerDto from) {
        Owner to = new Owner();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setPetClinicId(from.getPetClinicId());

        return to;
    }

    public OwnerDto ownerToOwnerDto(Owner from) {
        return new OwnerDto(from.getName(), from.getId(), from.getPetClinicId());
    }
}
