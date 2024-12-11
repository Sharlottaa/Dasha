package com.example.Dasha.repository;
import java.util.List;
import com.example.Dasha.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByBankId(Long bankId);
}
