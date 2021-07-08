package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Model.Customer;
import com.udacity.jdnd.course3.critter.Repository.CustomerJPARepository;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerRepository {

    CustomerJPARepository customerRepo;

    public CustomerService(CustomerJPARepository customerRepo) {
        this.customerRepo = customerRepo;
    }


    public Customer addCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }


    public Customer getCustomerById(Long id) {

        Optional<Customer> foundCustomer = customerRepo.findById(id);

        Customer customer = new Customer();

        if (foundCustomer.isPresent()) {
            customer.setId(foundCustomer.get().getId());
            customer.setName(foundCustomer.get().getName());
            customer.setPhoneNumber(foundCustomer.get().getPhoneNumber());
            customer.setPets(foundCustomer.get().getPets());
        }

        return customer;
    }
}
