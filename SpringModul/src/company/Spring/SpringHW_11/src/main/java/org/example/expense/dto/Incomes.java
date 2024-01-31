package org.example.expense.dto;

import java.math.BigDecimal;

public record Incomes(BigDecimal amount, String source, String description) {
    public Incomes(double amount, String source, String description) {
        this(BigDecimal.valueOf(amount), source, description);
    }

    // Добавим метод getSource
    public String getSource() {
        return source;
    }
}
