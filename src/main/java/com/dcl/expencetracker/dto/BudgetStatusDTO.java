package com.dcl.expencetracker.dto;

import java.math.BigDecimal;

public class BudgetStatusDTO {

    private BigDecimal budget;
    private BigDecimal spent;
    private BigDecimal remaining;
    private String status;

    public BudgetStatusDTO(
            BigDecimal budget,
            BigDecimal spent,
            BigDecimal remaining,
            String status) {

        this.budget = budget;
        this.spent = spent;
        this.remaining = remaining;
        this.status = status;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public BigDecimal getSpent() {
        return spent;
    }

    public BigDecimal getRemaining() {
        return remaining;
    }

    public String getStatus() {
        return status;
    }

}
