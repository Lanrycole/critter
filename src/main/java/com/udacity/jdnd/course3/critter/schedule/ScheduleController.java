package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.Model.Employees;
import com.udacity.jdnd.course3.critter.Model.Pet;
import com.udacity.jdnd.course3.critter.Model.Schedule;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import com.udacity.jdnd.course3.critter.Repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    ScheduleRepository scheduleRepo;
    EmployeeRepository employeeRepo;
    PetRepository petRepo;

    /**
     * Auto-wiring the above repositories using constructor
     * @param scheduleRepo
     * @param employeeRepo
     * @param petRepo
     */
    public ScheduleController(ScheduleRepository scheduleRepo,
                              EmployeeRepository employeeRepo,
                              PetRepository petRepo) {
        this.scheduleRepo = scheduleRepo;
        this.employeeRepo = employeeRepo;
        this.petRepo = petRepo;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule sch = scheduleRepo.createSchedule(convertScheduleDTOtoSchedule(scheduleDTO));
        return convertScheduleToDTO(sch);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleRepo.getAllSchedules();
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        schedules.forEach(schedule->{
            scheduleDTOList.add(convertScheduleToDTO(schedule));

        });

        return scheduleDTOList;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> scheduleList = scheduleRepo.getScheduleForPet(petId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

            scheduleList.forEach(schList -> {
                 scheduleDTOList.add(convertScheduleToDTO(schList));

        });
        return scheduleDTOList;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> scheduleList = scheduleRepo.getScheduleForEmployee(employeeId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        scheduleList.forEach(schList -> {
            scheduleDTOList.add(convertScheduleToDTO(schList));

        });
        return scheduleDTOList;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleList = scheduleRepo.getScheduleForCustomer(customerId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

            scheduleList.forEach(schList -> {
                scheduleDTOList.add(convertScheduleToDTO(schList));

        });
        return scheduleDTOList;
    }

    public ScheduleDTO convertScheduleToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        List<Pet> petsList = schedule.getPets();
        List<Employees> employeeList = schedule.getEmployees();

        List<Long> petIds = new ArrayList<>();
        List<Long> employeeIds = new ArrayList<>();

        if (!petsList.isEmpty()) {
            petsList.forEach(pet -> {
                petIds.add(pet.getId());
            });
        }

        if (!employeeList.isEmpty()) {
            employeeList.forEach(employee -> {
                employeeIds.add(employee.getId());
            });

        }
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }


    public Schedule convertScheduleDTOtoSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        List<Pet> petsList = new ArrayList<>();
        List<Employees> employeeList = new ArrayList<>();

        List<Long> petIds = scheduleDTO.getPetIds();
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();

        if (!petIds.isEmpty()) {
            petIds.forEach(id -> {
                Pet pet = petRepo.getPet(id);
                petsList.add(pet);

            });
        }

        if (!employeeIds.isEmpty()) {
            employeeIds.forEach(id -> {
                Employees emp = employeeRepo.findEmployeeById(id);
                employeeList.add(emp);
            });

        }

        schedule.setPets(petsList);
        schedule.setEmployees(employeeList);
        return schedule;
    }

}
