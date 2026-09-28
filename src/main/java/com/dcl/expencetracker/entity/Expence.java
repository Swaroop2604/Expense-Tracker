package com.dcl.expencetracker.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Expence {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    
	@Positive(message = "Amount must be greater than 0")
    private double amount;
    
	@NotBlank(message = "Description is required")
    private String description;
    
	@NotNull(message = "Date is required")
    private LocalDate date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

}
