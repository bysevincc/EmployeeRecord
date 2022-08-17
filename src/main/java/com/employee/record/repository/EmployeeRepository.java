package com.employee.record.repository;

import com.employee.record.entity.Department;
import com.employee.record.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {

    Optional<Employee> findByIdentity(Long identity);

    void deleteById(Long identity);

    List<Employee> findByDepartment(Department department);

    List<Employee> findByStartedDateGreaterThanAndSalaryGreaterThan(Date date, int salary);
}
