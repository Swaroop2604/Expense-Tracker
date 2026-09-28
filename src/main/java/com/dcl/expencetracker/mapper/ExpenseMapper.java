package com.dcl.expencetracker.mapper;

import com.dcl.expencetracker.dto.ExpenseRequestDTO;
import com.dcl.expencetracker.dto.ExpenseResponseDTO;
import com.dcl.expencetracker.entity.Category;
import com.dcl.expencetracker.entity.Expence;
import com.dcl.expencetracker.entity.User;

public class ExpenseMapper {
	
	public static Expence toEntity(
            ExpenseRequestDTO dto,
            User user,
            Category category) {

        Expence expense = new Expence();

        expense.setAmount(dto.getAmount());
        expense.setDescription(dto.getDescription());
        expense.setDate(dto.getDate());
        expense.setUser(user);
        expense.setCategory(category);

        return expense;
    }

    public static ExpenseResponseDTO toDTO(Expence expense) {

        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getDate(),
                expense.getUser().getId(),
                expense.getUser().getName(),
                expense.getCategory().getId(),
                expense.getCategory().getName()
        );
    }

}
