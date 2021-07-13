package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Model.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;

import java.util.List;

public interface ScheduleRepository {
     Schedule createSchedule(Schedule schedule);
     List<Schedule> getAllSchedules();
     List<Schedule> getScheduleForEmployee(long employeeId);
     List<Schedule> getScheduleForCustomer(long customerId);
     List<Schedule> getScheduleForPet(long petId);
}
