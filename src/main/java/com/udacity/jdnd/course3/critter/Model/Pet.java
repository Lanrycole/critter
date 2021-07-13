package com.udacity.jdnd.course3.critter.Model;

import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter
    @Setter
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private @Getter @Setter Customer owner;

    @Enumerated(EnumType.STRING)
    private @Getter @Setter PetType type;
    private LocalDate birthDate;

    private @Getter @Setter String notes;
    @Nationalized
    private @Getter @Setter String name;



}
