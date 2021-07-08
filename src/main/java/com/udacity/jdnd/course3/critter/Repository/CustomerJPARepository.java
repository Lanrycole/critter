package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerJPARepository extends JpaRepository<Customer, Long> {


//    @Query("UPDATE Company c SET c.address = :address WHERE c.id = :companyId"


}
