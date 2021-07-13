package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Model.Customer;
import com.udacity.jdnd.course3.critter.Model.Pet;
import com.udacity.jdnd.course3.critter.Repository.CustomerJPARepository;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.PetJPARepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService implements CustomerRepository {

    CustomerJPARepository customerRepo;
    PetJPARepository petRepo;


    /**
     *
     * @param customerRepo
     * @param petRepo
     */
    public CustomerService(CustomerJPARepository customerRepo,
                           PetJPARepository petRepo) {
        this.customerRepo = customerRepo;
        this.petRepo = petRepo;
    }

    /**
     *
     * @param customer
     * @return customer
     */
    public Customer addCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    /**
     *
     * @return list of customers
     */
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
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

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Customer getOwnerByPet(Long id) {
        Pet pet = petRepo.getOne(id);
        return customerRepo.getOne(pet.getOwner().getId());
    }


}
