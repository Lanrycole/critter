package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Model.Employees;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository {
    Employees addEmployee(Employees employee);
    List<Employees> getAllEmployees();
    Employees findEmployeesById(Long id);
}
