package com.learning.backend.repository;

import com.learning.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    Employee findByEmail(String email);
}
