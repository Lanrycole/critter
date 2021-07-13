package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Model.Customer;
import com.udacity.jdnd.course3.critter.Model.Pet;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.PetJPARepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService implements PetRepository {


    PetJPARepository petRepository;
    CustomerRepository customerRepo;

    /**
     *
     * @param petRepository
     * @param customerRepo
     */
    public PetService(PetJPARepository petRepository, CustomerRepository customerRepo) {
        this.petRepository = petRepository;
        this.customerRepo = customerRepo;
    }

    /**
     *
     * @param pet
     * @return saved Pet
     */
    @Override
    public Pet savePet(Pet pet) {
        Pet savedPet = petRepository.save(pet);
        Customer customer = savedPet.getOwner();
        if(customer.getId()!=null){
            savedPet.setOwner(customer);
        }
        customer.addPet(savedPet);
        customerRepo.addCustomer(customer);
        return savedPet;
    }

    /**
     *
     * @return list of Pets
     */
    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    /**
     *
     * @param petId
     * @return pet by ID
     */
    @Override
    public Pet getPet(long petId) {
        return petRepository.getOne(petId);
    }

    /**
     *
     * @param id
     * @return list of pet
     */
    @Override
    public List<Pet> getOwnerByPet(Long id) {
            return petRepository.getOwnerByPet(id);


    }


}
