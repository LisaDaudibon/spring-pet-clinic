package com.zenika.academy.poeirest.service;

import com.zenika.academy.poeirest.service.model.Owner;
import com.zenika.academy.poeirest.service.model.PetClinic;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OwnerService {
    List<Owner> owners;

    public OwnerService (List<Owner> owners) { this.owners = owners; }

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
}
