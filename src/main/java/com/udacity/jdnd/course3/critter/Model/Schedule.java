package com.udacity.jdnd.course3.critter.Model;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity(name = "Schedule")
public class Schedule {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    private List<Employees> employee;

    @OneToMany
    private List<Pet> pet;
    private LocalDate date;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
//    @CollectionTable(name="activities")
    @Column(name="activities")
    private Set<EmployeeSkill> activities;

    public List<Employees> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employees> employee) {
        this.employee = employee;
    }

    public List<Pet> getPetIds() {
        return pet;
    }

    public void setPetIds(List<Pet> pet) {
        this.pet = pet;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
