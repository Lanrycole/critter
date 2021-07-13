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

    public ScheduleController(ScheduleRepository scheduleRepo,
                              EmployeeRepository employeeRepo,
                              PetRepository petRepo) {
        this.scheduleRepo = scheduleRepo;
        this.employeeRepo = employeeRepo;
        this.petRepo = petRepo;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        scheduleRepo.createSchedule(convertScheduleDTOtoSchedule(scheduleDTO));
        return scheduleDTO;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedule = scheduleRepo.getAllSchedules();
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        if (!schedule.isEmpty()) {
            schedule.forEach(schdl -> {
                ScheduleDTO newSch = convertScheduleToDTO(schdl);
                scheduleDTOList.add(newSch);
            });

        } else {
            throw new IllegalStateException("Error retrieving schedules");
        }
        return scheduleDTOList;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> scheduleList = scheduleRepo.getScheduleForPet(petId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        if (!scheduleList.isEmpty()) {
            scheduleList.forEach(schList->{
                ScheduleDTO scheduleDTO = convertScheduleToDTO(schList);
                scheduleDTOList.add(scheduleDTO);
            });
        }
        return scheduleDTOList;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> scheduleList = scheduleRepo.getScheduleForEmployee(employeeId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        if (!scheduleList.isEmpty()) {
            scheduleList.forEach(schList->{
                ScheduleDTO scheduleDTO = convertScheduleToDTO(schList);
                scheduleDTOList.add(scheduleDTO);
            });
        }
        return scheduleDTOList;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleList = scheduleRepo.getScheduleForCustomer(customerId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        if (!scheduleList.isEmpty()) {
            scheduleList.forEach(schList->{
                ScheduleDTO scheduleDTO = convertScheduleToDTO(schList);
                scheduleDTOList.add(scheduleDTO);
            });
        }
        return scheduleDTOList;
    }

    public ScheduleDTO convertScheduleToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        List<Pet> petsList = petRepo.getAllPets();
        List<Employees> employeeList = employeeRepo.getAllEmployees();

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
