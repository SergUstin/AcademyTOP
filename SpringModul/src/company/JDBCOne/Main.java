package company.JDBCOne;

import java.sql.*;

public class Main {
    static String url = "jdbc:mysql://localhost:3306/top";
    static String user = "root";
    static String pass = "root";
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        try  {
            System.out.println("Первая таблица.................\n");
            coursesTable();
            System.out.println("\n\n");
            System.out.println("Вторая таблица.................\n");
            purchaselistTable();
            System.out.println("\n\n");
            System.out.println("Третья таблица.................\n");
            studentsTable();
            System.out.println("\n\n");
            System.out.println("Четвертая таблица.................\n");
            subscriptionsTable();
            System.out.println("\n\n");
            System.out.println("Пятая таблица.................\n");
            teachersTable();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void coursesTable() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM courses");

        System.out.printf("|%-3s|%-34s|%-10s|%-12s|%-78s|%-11s|%-15s|%-8s|%-15s|\n", "id", "courseName", "duration",
                "type", "description", "teacher_id", "students_count", "price", "price_per_hour");

        while (resultSet.next()) {
            System.out.printf("|%-3s|%-34s|%-10s|%-12s|%-78s|%-11s|%-15s|%-8s|%-15s|\n",
                    resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getInt("duration"), resultSet.getString("type"),
                    resultSet.getString("description"), resultSet.getInt("teacher_id"),
                    resultSet.getInt("students_count"), resultSet.getInt("price"),
                    resultSet.getInt("price_per_hour"));
        }

        statement.close();
        resultSet.close();
    }

    public static void purchaselistTable() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from purchaselist");

        System.out.printf("%-20s|%-35s|%-8s|%-20s|\n", "student_name", "course_name",
                "price", "subscription_date");

        while (resultSet.next()) {
            System.out.printf("%-20s|%-35s|%-8s|%-20s|\n", resultSet.getString("student_name"),
                    resultSet.getString("course_name"), resultSet.getInt("price"),
                    resultSet.getString("subscription_date"));
        }

        statement.close();
        resultSet.close();
    }

    public static void studentsTable() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from students");

        System.out.printf("|%-3s|%-21s|%-4s|%-20s|\n", "id", "name", "age", "registration_date");

        while (resultSet.next()) {
            System.out.printf("|%-3s|%-21s|%-4s|%-20s|\n", resultSet.getInt("id"),
                    resultSet.getString("name"), resultSet.getInt("age"),
                    resultSet.getString("registration_date"));
        }

        statement.close();
        resultSet.close();
    }

    public static void subscriptionsTable() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from subscriptions");

        System.out.printf("|%-11s|%-10s|%-20s|\n", "student_id", "course_id", "subscription_date");
        while (resultSet.next()) {
            System.out.printf("|%-11s|%-10s|%-20s|\n", resultSet.getInt("student_id"),
                    resultSet.getInt("course_id"), resultSet.getString("subscription_date"));
        }

        statement.close();
        resultSet.close();
    }

    public static void teachersTable() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from teachers");

        System.out.printf("|%-3s|%-23s|%-8s|%-4s|\n", "id", "name", "salary", "age");
        while (resultSet.next()) {
            System.out.printf("|%-3s|%-23s|%-8s|%-4s|\n", resultSet.getInt("id"),
                    resultSet.getString("name"), resultSet.getInt("salary"),
                    resultSet.getInt("age"));
        }

        statement.close();
        resultSet.close();
    }
}
