package org.example.expense.dto;

import java.math.BigDecimal;

public record Expense(BigDecimal amount, String description, String category) {
    public Expense(double amount, String description, String category) {
        this(BigDecimal.valueOf(amount), description, category);
    }

    // Добавим метод getCategory
    public String getCategory() {
        return category;
    }
}
