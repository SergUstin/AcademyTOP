package company.JDBCThree;

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
        try {
            System.out.println("Выборка данных");
            firstDataSample();
            System.out.println();
            secondDataSample();
            System.out.println();

            System.out.println("Фильтрация и сортировка");
            firstTaskIsFilteringAndSorting();
            System.out.println();
            secondTaskIsFilteringAndSorting();
            System.out.println();

            System.out.println("Группировка и агрегирование");
            firstTaskIsGroupingAndAggregation();
            System.out.println();
            secondTaskIsGroupingAndAggregation();
            System.out.println();

            System.out.println("Вставка данных");
            firstTaskIsInsertingData("Устин Сергей", 31, "2023-11-02 19:42:23");
            System.out.println();
            secondTaskIsInsertingData("Устин Сергей", "Java-разработчик", "2023-11-02 19:44:23");
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void firstDataSample() {
        String query = "SELECT name FROM courses";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void secondDataSample() {
        String query = "SELECT name FROM students";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void firstTaskIsFilteringAndSorting() {
        String query = "SELECT name, duration from courses where duration >= 30 order by duration DESC";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + ": " + resultSet.getInt("duration"));
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void secondTaskIsFilteringAndSorting() {
        String query = "select * from students where age > 25 order by name";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + ": " + resultSet.getInt("age"));
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void firstTaskIsGroupingAndAggregation() {
        String query = "select name, students_count from courses group by name, students_count";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + ": " + resultSet.getInt("students_count"));
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void secondTaskIsGroupingAndAggregation() {
        String query = "select name, AVG(price) as avg_price from courses group by name";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + ": " +
                        resultSet.getDouble("avg_price"));
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void firstTaskIsInsertingData(String name, int age, String registrationDate) {
        String query = "INSERT INTO students (name, age, registration_date) VALUE (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, registrationDate);

            int executeUpdate = statement.executeUpdate();

            if (executeUpdate > 0) {
                System.out.println("Запись добавлена успешна");
            } else {
                System.out.println("Произошла ошибка");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void secondTaskIsInsertingData(String studentName, String courseName, String registrationDate) {
        String query = "INSERT INTO purchaselist (student_name, course_name, subscription_date) VALUE (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, studentName);
            statement.setString(2, courseName);
            statement.setString(3, registrationDate);

            int executeUpdate = statement.executeUpdate();

            if (executeUpdate > 0) {
                System.out.println("Запись добавлена успешна");
            } else {
                System.out.println("Произошла ошибка");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
