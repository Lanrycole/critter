package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Model.Employees;
import com.udacity.jdnd.course3.critter.Repository.EmployeeJPARepository;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class EmployeeService implements EmployeeRepository {


    EmployeeJPARepository employeeRepo;

    /**
     *
     * @param employeeRepo
     */
    public EmployeeService(EmployeeJPARepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    /**
     *
     * @param employee
     * @return saved employee
     */
    @Override
    public Employees addEmployee(Employees employee) {
        return employeeRepo.save(employee);
    }

    /**
     *
     * @return List of Employees
     */
    @Override
    public List<Employees> getAllEmployees() {
        return employeeRepo.findAll();
    }

    /**
     *
     * @param id
     * @return an employee by ID
     */
    @Override
    public Employees findEmployeeById(Long id) {
        return  employeeRepo.getOne(id);
    }

    /**
     *
     * @param availability
     * @param id
     */
    @Override
    public void setAvailability(Set<DayOfWeek> availability, Long id) {
        Employees employee = employeeRepo.getOne(id);

        if (employee.getId() != null) {
            employee.setDaysAvailable(availability);
            employeeRepo.save(employee);
        } else {
            throw new IllegalStateException("Employee not found");
        }

    }

    /**
     *
     * @param skills
     * @param dayOfWeek
     * @return list of employees
     */
    @Override
    public List<Employees> getEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        List<Employees> employees = new ArrayList<>();
        List<Employees> employeesAvailability = employeeRepo.findAllAvailability(dayOfWeek);

        employeesAvailability.forEach(employee->{
            if (employee.getSkills().containsAll(skills)) {
                employees.add(employee);
            }
        });

        return employees;
    }

}
