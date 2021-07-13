package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface EmployeeJPARepository extends JpaRepository<Employees, Long> {
    @Query("SELECT e FROM Employees e WHERE :day MEMBER OF e.daysAvailable")
    List<Employees> findAllAvailability(@Param("day") DayOfWeek day);

}
