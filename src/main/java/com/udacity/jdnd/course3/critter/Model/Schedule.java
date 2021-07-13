package com.udacity.jdnd.course3.critter.Model;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter
    @Setter
    long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private @Getter @Setter List<Employees> employees;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private @Getter @Setter List<Pet> pets;

    private @Getter @Setter LocalDate date;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    private @Getter @Setter Set<EmployeeSkill> activities;


}
