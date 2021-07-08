package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Model.Employees;
import com.udacity.jdnd.course3.critter.Repository.EmployeeJPARepository;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeRepository {

    @Autowired
    EmployeeJPARepository employeeJPARepository;

    @Override
    public Employees addEmployee(Employees employee) {
        return employeeJPARepository.save(employee);
    }

    @Override
    public List<Employees> getAllEmployees() {
        return employeeJPARepository.findAll();
    }

    @Override
    public Employees findEmployeesById(Long id) {
        Optional<Employees> emp = employeeJPARepository.findById(id);
        Employees emps = new Employees();

        if(emp.isPresent()) {
         emps.setName(emp.get().getName());
         emps.setId(emp.get().getId());
         emps.setSkills(emp.get().getSkills());
         emps.setDaysAvailable(emp.get().getDaysAvailable());
        }else{
        throw new IllegalStateException("Employee not found");
        }

        System.out.println("Printing employees: "+emps);
        return emps;
    }


}
