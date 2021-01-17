package com.paypal.bfs.test.employeeserv.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    @Query("SELECT new com.paypal.bfs.test.employeeserv.api.EmployeeEntity(e.id) FROM EmployeeEntity e " +
            "WHERE e.firstName = :firstName " +
            "and e.lastName = :lastName " +
            "and e.dateOfBirth = :dateOfBirth " +
            "and e.addressLine1 = :addressLine1 " +
            "and e.city = :city " +
            "and e.state = :state " +
            "and e.country = :country " +
            "and e.zipCode = :zipCode"
    )
    EmployeeEntity findByAllFields(@Param("firstName") String firstName,
                                   @Param("lastName") String lastName,
                                   @Param("dateOfBirth") Date dateOfBirth,
                                   @Param("addressLine1") String addressLine1,
                                   @Param("city") String city,
                                   @Param("state") String state,
                                   @Param("country") String country,
                                   @Param("zipCode") String zipCode
                                   );
}

