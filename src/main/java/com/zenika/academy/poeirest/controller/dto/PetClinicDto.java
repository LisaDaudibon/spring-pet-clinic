package com.zenika.academy.poeirest.controller.dto;

public class PetClinicDto {

    public String name;

    private int id;

    public PetClinicDto(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public PetClinicDto() {}

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return name ;
    }

}
