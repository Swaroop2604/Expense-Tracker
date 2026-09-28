package com.dcl.expencetracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dcl.expencetracker.entity.Income;
import com.dcl.expencetracker.entity.User;
import com.dcl.expencetracker.exception.ResourceNotFoundException;
import com.dcl.expencetracker.repository.IncomeRepository;
import com.dcl.expencetracker.repository.UserRepository;

@Service
public class IncomeService {



    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    public IncomeService(
            IncomeRepository incomeRepository,
            UserRepository userRepository) {

        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
    }

    // Create income
    public Income createIncome(
            String email,
            Income income) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found with email: " + email
                    ));

        income.setUser(user);

        return incomeRepository.save(income);
    }

    // Get all income of logged-in user
    public List<Income> getAllIncome(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found with email: " + email
                    ));

        return incomeRepository.findByUserId(user.getId());
    }

}
