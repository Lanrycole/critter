package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Model.Customer;
import com.udacity.jdnd.course3.critter.Model.Pet;
import com.udacity.jdnd.course3.critter.Model.Schedule;
import com.udacity.jdnd.course3.critter.Repository.CustomerJPARepository;
import com.udacity.jdnd.course3.critter.Repository.ScheduleJPARepository;
import com.udacity.jdnd.course3.critter.Repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ScheduleService implements ScheduleRepository {


    ScheduleJPARepository scheduleRepo;
    CustomerJPARepository customerRepo;

    public ScheduleService(ScheduleJPARepository scheduleRepo, CustomerJPARepository customerRepo) {
        this.customerRepo = customerRepo;
        this.scheduleRepo = scheduleRepo;
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepo.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepo.findAll();
    }

    @Override
    public List<Schedule> getScheduleForEmployee(long employeeId) {
       return scheduleRepo.findAllByEmployees_Id(employeeId);
    }

    @Override
    public List<Schedule> getScheduleForCustomer(long customerId) {
        return scheduleRepo.findAllByCustomerId(customerId);

//        Optional<Customer> optionalUser = customerRepo.findById(customerId);
//        Customer customer = optionalUser.orElse(null);
//        List<Pet> pets = Objects.requireNonNull(customer).getPets();
//        ArrayList<Schedule> schedules = new ArrayList<>();
//        for (Pet pet : pets) {
//            schedules.addAll(scheduleRepo.findAllByPets_Id(pet.getId()));
//        }
//        return schedules;

    }

    @Override
    public List<Schedule> getScheduleForPet(long petId) {
        return scheduleRepo.findAllByPets_Id(petId);
    }
}
