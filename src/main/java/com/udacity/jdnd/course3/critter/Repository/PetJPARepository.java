package com.udacity.jdnd.course3.critter.Repository;


import com.udacity.jdnd.course3.critter.Model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetJPARepository extends JpaRepository <Pet, Long> {
    @Query("select p from Pet p where p.owner.id =:id")
    List<Pet> getPetsByOwner(Long id);
}
