package org.example.expense.dto;

import java.util.List;

public record TransactionReport(List<Expense> expenses, List<Incomes> incomes) {
    public List<Expense> getExpenses() {
        return expenses;
    }

    public List<Incomes> getIncomes() {
        return incomes;
    }
}

