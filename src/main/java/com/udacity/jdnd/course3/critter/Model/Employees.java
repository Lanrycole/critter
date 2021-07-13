package com.udacity.jdnd.course3.critter.Model;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity(name = "Employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "skills")
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    @Enumerated(EnumType.STRING)
     @Column(name = "daysAvailable")
    private Set<DayOfWeek> daysAvailable;

    private String name;

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Employees(long id, String name, Set<EmployeeSkill> skills) {
        this.id = id;
        this.name = name;
        this.skills = skills;
    }

    public Employees() {

    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + getId() +
                "skills=" + skills +
                ", daysAvailable=" + daysAvailable +
                ", name='" + name + '\'' +
                '}';
    }


}
