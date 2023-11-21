package company.HibernateAndORM;

import java.sql.*;

public class Lessons01 {
    static String url = "jdbc:mysql://localhost:3306/new_top";
    static String user = "root";
    static String pass = "root";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            System.out.println("Добавление студентов");
            insertStudent(connection, "Serg", 18);
            insertStudent(connection, "Igor", 19);
            System.out.println();

            System.out.println("Все студенты:" );
            printStudents(connection);

            System.out.println("Добавление курсов");
            insertCourses(connection, "Программист 1С-Битрикс", 50);
            insertCourses(connection, "С#-разработчик с нуля", 50);
            System.out.println();

            System.out.println("Вывод таблицы курсы");
            printCourses(connection);
            System.out.println();

            System.out.println("Вывод студентов с фильтром");
            printStudentsWithFilter(connection, 20);
            System.out.println();

            System.out.println("Вывод курсов с фильтром");
            printCoursesWithFilter(connection, 50);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertStudent(Connection connection, String name, int age) throws SQLException {
        String insertStudentQuery = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudentQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.executeUpdate();
        }
    }

    private static void printStudents(Connection connection) throws SQLException {
        String selectStudentsQuery = "SELECT * FROM students";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectStudentsQuery)) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Имя: " + resultSet.getString("name") +
                        ", Возраст: " + resultSet.getInt("age"));
            }

        }
    }

    private static void insertCourses(Connection connection, String courseName, int duration) throws SQLException {
        String insertCoursesQuery = "INSERT INTO courses (name, duration) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCoursesQuery)) {
            preparedStatement.setString(1, courseName);
            preparedStatement.setInt(2, duration);

            preparedStatement.executeUpdate();
        }
    }

    private static void printCourses(Connection connection) throws SQLException {
        String selectStudentsQuery = "SELECT * FROM courses";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectStudentsQuery)) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Название курса: " + resultSet.getString("name") +
                        ", Продолжительность: " + resultSet.getInt("duration"));
            }
        }
    }

    private static void printStudentsWithFilter(Connection connection, int age) throws SQLException {
        String selectStudentsQuery = "SELECT * FROM students WHERE age > ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectStudentsQuery)) {
            preparedStatement.setInt(1, age);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id") +
                            ", Название курса: " + resultSet.getString("name") +
                            ", Продолжительность: " + resultSet.getInt("age"));
                }
            }
        }
    }

    private static void printCoursesWithFilter(Connection connection, int duration) throws SQLException {
        String selectStudentsQuery = "SELECT * FROM courses WHERE duration < ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectStudentsQuery)) {
            preparedStatement.setInt(1, duration);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id") +
                            ", Название курса: " + resultSet.getString("name") +
                            ", Продолжительность: " + resultSet.getInt("duration"));
                }
            }
        }
    }
}
