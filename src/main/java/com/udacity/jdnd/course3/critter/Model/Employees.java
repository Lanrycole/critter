package com.udacity.jdnd.course3.critter.Model;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  @Getter @Setter Long id;


    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "skills")
    private  @Getter @Setter Set<EmployeeSkill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    @Enumerated(EnumType.STRING)
     @Column(name = "daysAvailable")
    private  @Getter @Setter Set<DayOfWeek> daysAvailable;

    @Nationalized
    private  @Getter @Setter String name;



}
