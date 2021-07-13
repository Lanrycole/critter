package com.udacity.jdnd.course3.critter.Model;


import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  @Getter @Setter Long id;

    @Nationalized
    private  @Getter @Setter String name;
    private  @Getter @Setter String phoneNumber;
    private  @Getter @Setter String notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.LAZY)
    private  @Getter @Setter List<Pet> pets;


    public void addPet(Pet pet) {
        if (pets == null) {
            pets = new ArrayList<>();
        }
        this.pets.add(pet);
    }

}
