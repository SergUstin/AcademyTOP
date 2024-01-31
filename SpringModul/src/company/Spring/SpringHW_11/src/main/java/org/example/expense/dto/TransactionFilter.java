package org.example.expense.dto;

public record TransactionFilter(String expenseCategory, String incomeSource) {
    public String getExpenseCategory() {
        return expenseCategory;
    }

    public String getIncomeSource() {
        return incomeSource;
    }
}
