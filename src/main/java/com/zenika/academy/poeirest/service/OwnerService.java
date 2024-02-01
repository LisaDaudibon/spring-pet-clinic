package com.zenika.academy.poeirest.service;

import com.zenika.academy.poeirest.service.model.Owner;
import com.zenika.academy.poeirest.service.model.PetClinic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class OwnerService {
    List<Owner> owners;

    private final PetClinicService petClinicService;

    public OwnerService (List<Owner> owners, PetClinicService petClinicService) {
        this.owners = owners;
        this.petClinicService = petClinicService;
    }

    public List<Owner> findAll() {
        return this.owners;
    }

    private int getNextId() {
        final int nextId;
        if (owners.isEmpty()) {
            nextId = 1;
        } else {
            nextId = Collections.max(owners.stream() .map(Owner::getId).toList()) + 1;
        }
        return nextId;
    }

    public Owner findById(int searchedId){
        for (Owner owner : owners){
            if (owner.getId() == searchedId) {
                return owner;
            }
        }
        return null;
    }

    private static boolean isOwnerInExistingPetClinic(Owner newOwner, PetClinic currentPetClinic) {
        return Objects.equals(newOwner.getPetClinicId(), currentPetClinic.getId());
    }

    public Owner add(Owner newOwner) throws NullPointerException {
        int nextId = this.getNextId(); // Use the getNextId method to get the next available ID
        List<PetClinic> petClinics = new ArrayList<>();
        petClinics = petClinicService.findAll();
        // Set the ID for the Owner
        newOwner.setId(nextId);

        for (PetClinic currentPetClinic : petClinics) {
            if (isOwnerInExistingPetClinic(newOwner, currentPetClinic)){
                owners.add(newOwner);
                return newOwner;
            }
        }
        throw new NullPointerException();
    }

    public void deleteById(int searchedId) {
        for (Owner owner : owners) {
            if (owner.getId() == searchedId) {
                owners.remove(owner);
                return;
            }
        }
    }

    public void updateById(int searchedId, Owner newOwner ) throws NullPointerException {
        for (Owner owner : owners) {
             if (owner.getId() == searchedId) {
                owner.setName(newOwner.getName());
                return;
            } else {
                throw new NullPointerException();
            }
        }
    }
}
