package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.Model.Customer;
import com.udacity.jdnd.course3.critter.Model.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
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

    PetService petService;
    CustomerService customerService;

    /**
     *
     * @param petRepository
     * @param customerRepo
     */

    /**
     * @param petService
     * @param customerService
     */
    public PetController(PetService petService, CustomerService customerService) {
        this.petService = petService;
        this.customerService = customerService;
    }

    /**
     * @param petDTO
     * @return
     */
    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet savedPet = convertPetDTotoPet(petDTO);
        Customer customer = customerService.getCustomerById(petDTO.getOwnerId());
        if (customer.getId() != null) {
            savedPet.setOwner(customer);
            Pet savP = petService.savePet(savedPet);
            petDTO.setOwnerId(savP.getOwner().getId());
            petDTO.setId(savP.getId());
            return petDTO;
        } else {
            throw new IllegalStateException("Customer with Id: "
                    + petDTO.getOwnerId() + " does not exist");
        }

    }

    /**
     * @param petId
     * @return
     */
    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetToPetDTO(petService.getPet(petId));
    }

    /**
     * @returns List of PetDTO
     */
    @GetMapping
    public List<PetDTO> getPets() {
        return convertPetToPetDTO(petService.getAllPets());
    }

    /**
     * @param ownerId
     * @return
     */
    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return convertPetToPetDTO(petService.getOwnerByPet(ownerId));
    }

    /**
     * @param petDTO
     * @return
     */
    private Pet convertPetDTotoPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    /**
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
