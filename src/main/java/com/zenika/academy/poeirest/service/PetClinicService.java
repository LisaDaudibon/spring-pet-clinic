package com.zenika.academy.poeirest.service;

import com.zenika.academy.poeirest.service.model.PetClinic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.lang.String.valueOf;

@Service
public class PetClinicService {
    List<PetClinic> petClinics = new ArrayList<>();
    public PetClinicService(List<PetClinic> petClinics) {
        this.petClinics = petClinics;
    }

    public List<PetClinic> findAll() {
        return this.petClinics;
    }
    private int getNextId() {
        final int nextId;
        if (petClinics.isEmpty()) {
            nextId = 1;
        } else {
            nextId = Collections.max(petClinics.stream() .map(PetClinic::getId).toList()) + 1;
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
        for (PetClinic petClinic : petClinics){
            if (petClinic.getId() == searchedId) {
                return petClinic;
            }
        }
        return null;
    }

    private static boolean isPetClinicExists(PetClinic petClinic, PetClinic currentPetClinic) {
        return currentPetClinic.getName().equals(petClinic.getName());
    }

    public PetClinic add(PetClinic petClinic) throws NullPointerException {
        int nextId = this.getNextId(); // Use the getNextId method to get the next available ID
        PetClinic petClinicAdded;
        String message = "Il existe déjà une clinique avec ce nom là";

        // Set the ID for the PetClinicDto
        petClinic.setId(nextId);


        for (PetClinic currentPetClinic : petClinics) {
            if (isPetClinicExists(petClinic, currentPetClinic)){
                throw new NullPointerException();
            }
        }

        petClinics.add(petClinic);
        return petClinic;
    }

    public void deleteById(int searchedId) {
        for (PetClinic petClinic : petClinics) {
            if (petClinic.getId() == searchedId) {
                petClinics.remove(petClinic);
                return;
            }
        }
    }

    public void updateById(int searchedId, PetClinic newPetClinic) throws NullPointerException {
        for (PetClinic petClinic : petClinics) {
            if (isPetClinicExists(petClinic, newPetClinic)){
                throw new NullPointerException();
            }
            else if (petClinic.getId() == searchedId) {
                petClinic.setName(newPetClinic.getName());
                return;
            }
        }
    }

}
