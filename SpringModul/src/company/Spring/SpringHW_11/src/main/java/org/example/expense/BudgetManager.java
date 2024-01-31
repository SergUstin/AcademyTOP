package org.example.expense;

import org.example.expense.dto.Expense;
import org.example.expense.dto.Incomes;
import org.example.expense.dto.TransactionFilter;
import org.example.expense.dto.TransactionReport;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BudgetManager {
    private final List<Expense> expenses;
    private final List<Incomes> incomes;

    public BudgetManager() {
        expenses = new ArrayList<>();
        incomes = new ArrayList<>();
    }

    // todo Методы добавления доходов и расходов
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void addIncomes(Incomes income) {
        incomes.add(income);
    }

    // todo Метод расчета среднего дохода
    public Optional<BigDecimal> averageIncome() {
        if (incomes.isEmpty()) {
            return Optional.empty(); // Optional.of(new BigDecimal(0));
        }

        var incomeCount = new BigDecimal(incomes.size());
        return Optional.of(getTotalIncomes().divide(incomeCount, RoundingMode.FLOOR));
    }


    // todo Методы расчета общей суммы расходов и доходов
    public BigDecimal getTotalIncomes() {
        return incomes
                .stream()
                .map(Incomes::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalExpenses() {
        return expenses
                .stream()
                .map(Expense::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // todo Метод получения разницы между суммой доходов и между суммой расходов
    public BigDecimal getBalance() {
        return getTotalIncomes().subtract(getTotalExpenses());
    }

    // Метод для генерации отчета по транзакциям
    public TransactionReport generateTransactionReport(TransactionFilter filter) {
        List<Expense> filteredExpenses = filterExpenses(filter, expenses);
        List<Incomes> filteredIncomes = filterIncomes(filter, incomes);
        return new TransactionReport(filteredExpenses, filteredIncomes);
    }

    // Логика фильтрации расходов
    private List<Expense> filterExpenses(TransactionFilter filter, List<Expense> expenses) {
        if (filter != null && filter.getExpenseCategory() != null) {
            return expenses.stream()
                    .filter(expense -> expense.getCategory().equals(filter.getExpenseCategory()))
                    .collect(Collectors.toList());
        }
        return expenses;
    }

    // Логика фильтрации доходов
    private List<Incomes> filterIncomes(TransactionFilter filter, List<Incomes> incomes) {
        if (filter != null && filter.getIncomeSource() != null) {
            return incomes.stream()
                    .filter(income -> income.getSource().equals(filter.getIncomeSource()))
                    .collect(Collectors.toList());
        }
        return incomes;
    }
}