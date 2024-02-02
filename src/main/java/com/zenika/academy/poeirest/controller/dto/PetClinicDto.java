package com.zenika.academy.poeirest.controller.dto;

import com.zenika.academy.poeirest.service.model.Owner;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class PetClinicDto {
    @NotBlank
    public String name;

    private int id;
    private List<OwnerDto> owners;

    public PetClinicDto(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public PetClinicDto() {}

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public List<OwnerDto> getOwners() {
        return owners;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setOwners(List<OwnerDto> owners) {
        this.owners = owners;
    }

    @Override
    public String toString() {
        return name ;
    }

}
