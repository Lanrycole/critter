package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ScheduleJPARepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByEmployeesId(long id);
    List<Schedule> findAllByPetsId(long id);

    @Query("SELECT s FROM Schedule s JOIN s.pets p JOIN p.owner o WHERE o.id=:id")
    List<Schedule> findAllByCustomerId(@Param("id") long id);
}
