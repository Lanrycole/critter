package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Model.Customer;
import com.udacity.jdnd.course3.critter.Model.Pet;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.PetJPARepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService implements PetRepository {


    PetJPARepository petRepository;
    CustomerRepository customerRepo;

    public PetService(PetJPARepository petRepository, CustomerRepository customerRepo) {
        this.petRepository = petRepository;
        this.customerRepo = customerRepo;
    }

    @Override
    public Pet savePet(Pet pet) {
        Pet savedPet = petRepository.save(pet);
        Customer customer = savedPet.getOwner();
        customer.addPet(savedPet);
        customerRepo.addCustomer(customer);

        return savedPet;
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPet(long petId) {
        return petRepository.getOne(petId);
    }

    @Override
    public List<Pet> getPetsByOwner(Long id) {

        return petRepository.getPetsByOwner(id);
    }

}
