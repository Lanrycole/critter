package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Model.Schedule;
import com.udacity.jdnd.course3.critter.Repository.ScheduleJPARepository;
import com.udacity.jdnd.course3.critter.Repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService implements ScheduleRepository {


    ScheduleJPARepository scheduleRepo;

    public ScheduleService(ScheduleJPARepository scheduleRepo) {
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
       return scheduleRepo.findAllByEmployeesId(employeeId);
    }

    @Override
    public List<Schedule> getScheduleForCustomer(long customerId) {
        return scheduleRepo.findAllByCustomerId(customerId);
    }

    @Override
    public List<Schedule> getScheduleForPet(long petId) {
        return scheduleRepo.findAllByPetsId(petId);
    }
}
