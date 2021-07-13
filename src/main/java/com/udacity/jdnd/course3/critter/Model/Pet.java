package com.udacity.jdnd.course3.critter.Model;

import com.udacity.jdnd.course3.critter.pet.PetType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer owner;

    @Enumerated(EnumType.STRING)
    private PetType type;

    public Customer getOwner() {
        return owner;
    }
    public void setOwner(Customer customer) {
        this.owner = customer;
    }
    private LocalDate birthDate;

    private String notes;
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public PetType getType() {
        return type;
    }
    public void setType(PetType type) {
        this.type = type;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public Pet(long id,
               String name,
               PetType type,
               Customer owner,
               LocalDate birthDate, String notes) {
        this.type = type;
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
        this.notes = notes;
        this.id = id;

    }

    public Pet() {
    }

    @Override
    public String toString() {
        return "Pet{" +
                "ID=" + getId() +
                "type=" + type +
                ", birthDate=" + birthDate +
                ", notes='" + notes + '\'' +
                '}';
    }


}
