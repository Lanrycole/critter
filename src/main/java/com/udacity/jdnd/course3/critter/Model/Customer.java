package com.udacity.jdnd.course3.critter.Model;


import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;
    private String phoneNumber;
    private String notes;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.LAZY)
     private List<Pet> pets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }


    /**
     *
     * @param id
     * @param name
     * @param phoneNumber
     * @param notes
     * @param pets
     */
    public Customer(Long id, String name, String phoneNumber, String notes, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }

    public void addPet(Pet pet) {
        if (pets == null) {
            pets = new ArrayList<>();
        }
        this.pets.add(pet);
    }
    public Customer() {

    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + getId() + '\'' +
                "phoneNumber='" + phoneNumber + '\'' +
                ", notes='" + notes + '\'' +
                ", name='" + name + '\'' +
                ", pets=" + pets +
                '}';
    }


}
