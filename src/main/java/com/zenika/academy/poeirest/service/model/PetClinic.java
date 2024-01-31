package com.zenika.academy.poeirest.service.model;

import org.springframework.stereotype.Service;


public class PetClinic {
    private int id;
    private String name;

    public PetClinic(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
