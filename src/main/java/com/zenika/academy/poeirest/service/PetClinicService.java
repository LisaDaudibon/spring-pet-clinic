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

    private boolean isPetClinicExists(String newPetClinic) {
        return petClinics.stream().anyMatch(petClinic -> petClinic.getName().contentEquals(newPetClinic));
    }

    public PetClinic add(PetClinic newPetClinic) throws NullPointerException {
        int nextId = this.getNextId();

        // Set the ID for the PetClinicDto
        newPetClinic.setId(nextId);


        if (isPetClinicExists(newPetClinic.getName())){
            throw new NullPointerException(String.format("A pet clinic with name %s already exists", newPetClinic.getName()));
        }

        petClinics.add(newPetClinic);
        return newPetClinic;
    }

    public void deleteById(int searchedId) {
        for (PetClinic petClinic : petClinics) {
            if (petClinic.getId() == searchedId) {
                petClinics.remove(petClinic);
                return;
            }
        }
    }

    public PetClinic updateById(int searchedId, PetClinic petClinicUpdated) throws Exception {
        List <PetClinic> petClinics = findAll();

        if (petClinics.isEmpty()) {
            throw new Exception(String.format("A pet clinic with name %s already exists", petClinicUpdated.getName()));
        }

        PetClinic petClinicFound = getById(searchedId);
        if (Objects.nonNull(petClinicFound)) {
            petClinicFound.setName(petClinicUpdated.getName());
        }

        return petClinicFound;
    }
}
