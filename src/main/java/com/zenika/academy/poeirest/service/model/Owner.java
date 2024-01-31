package com.zenika.academy.poeirest.service.model;

public class Owner {
    String name;
    int id;
    int petClinicId;

    public Owner (){

    }

    public Owner(String name, int id, int petClinicId) {
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
