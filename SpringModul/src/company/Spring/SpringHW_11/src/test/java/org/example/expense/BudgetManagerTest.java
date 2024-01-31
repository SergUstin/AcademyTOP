package org.example.expense;

import org.example.expense.dto.Expense;
import org.example.expense.dto.Incomes;
import org.example.expense.dto.TransactionFilter;
import org.example.expense.dto.TransactionReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetManagerTest {
    private BudgetManager budgetManager;

    @BeforeEach
    public void setUp() {
        budgetManager = new BudgetManager();
    }

    @Test
    public void testAddExpense() {
        Expense expense = new Expense(new BigDecimal("200"), "Обед", "Еда");
        budgetManager.addExpense(expense);
        var totalExpenses = budgetManager.getTotalExpenses();

        assertEquals(new BigDecimal("200"), totalExpenses);
    }

    @Test
    public void testAddIncomes() {
        Incomes income = new Incomes(new BigDecimal("1000"), "Salary", "main");
        budgetManager.addIncomes(income);
        var totalIncomes = budgetManager.getTotalIncomes();

        assertEquals(new BigDecimal("1000"), totalIncomes);
    }

    @Test
    public void testAverageIncomeEmptyIncomes() {
        Optional<BigDecimal> average = budgetManager.averageIncome();
        assertFalse(average.isPresent());
    }

    @Test
    public void testAverageIncomeNonEmptyIncomes() {
        Incomes income1 = new Incomes(new BigDecimal("1000.00"), "Salary", "Monthly salary");
        Incomes income2 = new Incomes(new BigDecimal("1500.00"), "Bonus", "Year end bonus");
        budgetManager.addIncomes(income1);
        budgetManager.addIncomes(income2);

        Optional<BigDecimal> average = budgetManager.averageIncome();
        assertTrue(average.isPresent());
        assertEquals(new BigDecimal("1250.00"), average.get());
    }

    @Test
    public void testGetTotalIncomesEmptyIncomes() {
        BigDecimal total = budgetManager.getTotalIncomes();
        assertEquals(BigDecimal.ZERO, total);
    }

    @Test
    public void testGetTotalIncomesNonEmptyIncomes() {
        Incomes income1 = new Incomes(new BigDecimal("1000.00"), "Salary", "Monthly salary");
        Incomes income2 = new Incomes(new BigDecimal("1500.00"), "Bonus", "Year end bonus");
        budgetManager.addIncomes(income1);
        budgetManager.addIncomes(income2);

        BigDecimal total = budgetManager.getTotalIncomes();
        assertEquals(new BigDecimal("2500.00"), total);
    }

    @Test
    public void testGetTotalExpensesEmptyExpenses() {
        BigDecimal total = budgetManager.getTotalExpenses();
        assertEquals(BigDecimal.ZERO, total);
    }

    @Test
    public void testGetTotalExpensesNonEmptyExpenses() {
        Expense expense1 = new Expense(new BigDecimal("500.00"), "Rent", "Monthly rent");
        Expense expense2 = new Expense(new BigDecimal("300.00"), "Groceries", "Weekly groceries");
        budgetManager.addExpense(expense1);
        budgetManager.addExpense(expense2);

        BigDecimal total = budgetManager.getTotalExpenses();
        assertEquals(new BigDecimal("800.00"), total);
    }

    @Test
    public void testGetBalance() {
        budgetManager.addIncomes(new Incomes(new BigDecimal("2000.00"), "Salary", "Monthly salary"));
        budgetManager.addExpense(new Expense(new BigDecimal("500.00"), "Rent", "Monthly rent"));
        budgetManager.addExpense(new Expense(new BigDecimal("300.00"), "Groceries", "Weekly groceries"));

        BigDecimal balance = budgetManager.getBalance();
        assertEquals(new BigDecimal("1200.00"), balance);
    }

    @Test
    public void testGenerateTransactionReport() {
        budgetManager.addIncomes(new Incomes(new BigDecimal("2000.00"), "Salary", "Monthly salary"));
        budgetManager.addExpense(new Expense(new BigDecimal("500.00"), "Rent", "Monthly rent"));
        budgetManager.addExpense(new Expense(new BigDecimal("300.00"), "Groceries", "Weekly groceries"));

        TransactionFilter filter = new TransactionFilter("Monthly rent", "Salary");
        TransactionReport report = budgetManager.generateTransactionReport(filter);

        // Проверяем, что отчет содержит только те транзакции, которые попадают под фильтр
        assertEquals(1, report.getExpenses().size());
        assertEquals(1, report.getIncomes().size());
    }

}