package com.zenika.academy.poeirest.controller.dto;

public class OwnerDto {
    String name;
    int id;
    int petClinicId;

    public OwnerDto (){

    }

    public OwnerDto(String name, int id, int petClinicId) {
        this.name = name;
        this.id = id;
        this.petClinicId = petClinicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetClinicId() {
        return petClinicId;
    }

    public void setPetClinicId(int petClinicId) {
        this.petClinicId = petClinicId;
    }
}
