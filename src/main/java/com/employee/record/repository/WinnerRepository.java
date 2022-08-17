package com.employee.record.repository;

import com.employee.record.entity.Winner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WinnerRepository extends CrudRepository<Winner,Long> {
    Optional<Winner> findByYearAndMonth(int year, int month);
}