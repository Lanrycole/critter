package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Model.Customer;
import com.udacity.jdnd.course3.critter.Model.Pet;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PetRepository {

    Pet savePet(Pet pet);
    List<Pet> getAllPets();
    Pet getPet(long petId);
    List<Pet> getPetsByOwner(Long id);

}
