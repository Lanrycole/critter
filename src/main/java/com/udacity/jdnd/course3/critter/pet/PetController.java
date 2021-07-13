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

    /**
     *
     * @param petRepository
     * @param customerRepo
     */

    /**
     *
     * @param petRepository
     * @param customerRepo
     */
    public PetController(PetRepository petRepository, CustomerRepository customerRepo) {
        this.petRepository = petRepository;
        this.customerRepo = customerRepo;
    }

    /**
     *
     * @param petDTO
     * @return
     */
    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet savedPet = convertPetDTotoPet(petDTO);
        Customer customer = customerRepo.getCustomerById(petDTO.getOwnerId());
        if (customer.getId() != null) {
            savedPet.setOwner(customer);
            Pet savP = petRepository.savePet(savedPet);
            petDTO.setOwnerId(savP.getOwner().getId());
            petDTO.setId(savP.getId());
            return petDTO;
        } else {
            throw new IllegalStateException("Add pet to a customer");
        }

    }

    /**
     *
     * @param petId
     * @return
     */
    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetToPetDTO(petRepository.getPet(petId));
    }

    /**
     *
     * @returns List of PetDTO
     */
    @GetMapping
    public List<PetDTO> getPets() {
        return convertPetToPetDTO(petRepository.getAllPets());
    }

    /**
     *
     * @param ownerId
     * @return
     */
    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return convertPetToPetDTO(petRepository.getOwnerByPet(ownerId));
    }

    /**
     *
     * @param petDTO
     * @return
     */
    private Pet convertPetDTotoPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    /**
     *
     * @param pet
     * @return
     */
    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        if (pet.getOwner() != null) {
            petDTO.setOwnerId(pet.getId());
        }
        return petDTO;
    }

    /**
     *
     * @param pets
     * @return List of PetDTO
     */
    private List<PetDTO> convertPetToPetDTO(List<Pet> pets) {
        List<PetDTO> petDToList = new ArrayList<>();
        pets.forEach(e -> {
            PetDTO pdto = new PetDTO();
            BeanUtils.copyProperties(e, pdto);
            pdto.setOwnerId(e.getOwner().getId());
            petDToList.add(pdto);
        });
        return petDToList;
    }
}
