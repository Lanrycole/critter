package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.Model.Customer;
import com.udacity.jdnd.course3.critter.Model.Pet;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    PetRepository petRepository;
    CustomerRepository customerRepo;

    public PetController(PetRepository petRepository, CustomerRepository customerRepo) {
        this.petRepository = petRepository;
        this.customerRepo = customerRepo;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
            Pet savedPet = convertPetDTotoPet(petDTO);
            petDTO.setId(savedPet.getId());
            petDTO.setOwnerId(savedPet.getOwner().getId());

        return petDTO;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        PetDTO savedPet= convertPetToPetDTO(petRepository.getPet(petId));
        savedPet.setOwnerId(customerRepo.getCustomerById(savedPet.getOwnerId()).getId());
        return savedPet;
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return convertPetToPetDTO(petRepository.getAllPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return convertPetToPetDTO(petRepository.getPetsByOwner(ownerId));
    }


    private Pet convertPetDTotoPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);

        Long customerId = petDTO.getOwnerId();
        Customer customer = customerRepo.getCustomerById(customerId);

        pet.setOwner(customer);

        return petRepository.savePet(pet);
    }

    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);

        if(pet.getOwner() !=null){
            petDTO.setOwnerId(pet.getId());
        }
        return petDTO;
    }

    private List<PetDTO> convertPetToPetDTO(List<Pet> pets) {
        List<PetDTO> petDToList = new ArrayList<>();
        pets.forEach(e -> {
            PetDTO pdto = new PetDTO();
            BeanUtils.copyProperties(e, pdto);
            petDToList.add(pdto);
        });
        return petDToList;
    }


}
