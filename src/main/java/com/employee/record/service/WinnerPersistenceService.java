package com.employee.record.service;

import com.employee.record.entity.Winner;
import com.employee.record.repository.WinnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WinnerPersistenceService {
    private final WinnerRepository winnerRepository;

    public Optional<Winner> getWinnerOfGivenDate(int year, int month) {
        return winnerRepository.findByYearAndMonth(year,month);
    }
}
