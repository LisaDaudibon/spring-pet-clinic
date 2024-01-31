package com.zenika.academy.poeirest.service;

import com.zenika.academy.poeirest.service.model.PetClinic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PetClinicService {
    List<PetClinic> petClinicList = new ArrayList<>();
    public PetClinicService(List<PetClinic> petClinicList) {
        this.petClinicList = petClinicList;
    }

    public List<PetClinic> findAll() {
        return this.petClinicList;
    }
    private int getNextId() {
        final int nextId;
        if (petClinicList.isEmpty()) {
            nextId = 1;
        } else {
            nextId = Collections.max(petClinicList.stream() .map(PetClinic::getId).toList()) + 1;
        }
        return nextId;
    }

//    public Optional<PetClinic> getById (int searchedId) {
//        return petClinicList.stream()
//                .filter( petClinic -> petClinic.getId() == searchedId)
//                .findFirst();
//    }
//
    public PetClinic getById(int searchedId){
        for (PetClinic petClinic : petClinicList ){
            if (petClinic.getId() == searchedId) {
                return petClinic;
            }
        }
        return null;
    }

    public PetClinic addPetClinic (PetClinic petClinic){
        int nextId = this.getNextId(); // Use the getNextId method to get the next available ID

        // Set the ID for the PetClinicDto
        petClinic.setId(nextId);
        petClinicList.add(petClinic);
        return petClinic;
    }

    public void deleteById(int searchedId) {
        for (PetClinic petClinic : petClinicList) {
            if (petClinic.getId() == searchedId) {
                petClinicList.remove(petClinic);
                return;
            }
        }
    }

    public void updateById(int searchedId, PetClinic newPetClinic) {
        for (PetClinic petClinic : petClinicList) {
            if (petClinic.getId() == searchedId) {
                petClinic.setName(newPetClinic.getName());
                return;
            }
        }
    }
}
