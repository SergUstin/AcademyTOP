package company.JDBCTwo;

import java.sql.*;

public class Main {
    static String url = "jdbc:mysql://localhost:3306/top?userName=root&password=root";
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

            System.out.println("Вывод данных меньше 30");
            getSampleByTheHour();
            System.out.println("\n");

            addNewCourse("Scrum", 20, "MANAGEMENT",
                    "Представляем вашему вниманию шикарный курс Scrum", 150, 20000);

            changeThePriceOfTheCourseType(6000, "PROGRAMMING");

            deletingACourseByItsDuration(10);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void getSampleByTheHour() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name, duration, type FROM courses where duration >= 30");

        System.out.printf("|%-34s|%-9s|%-12s|\n", "name", "duration", "type");

        while (resultSet.next()) {
            System.out.printf("|%-34s|%-9s|%-12s|\n", resultSet.getString("name"),
                    resultSet.getString("duration"), resultSet.getString("type"));
        }

        resultSet.close();
        statement.close();
    }

    public static void addNewCourse(String name, int duration, String type, String description, int students_count, double price) {
        String query = "INSERT INTO courses (name, duration, type, description, students_count, price, price_per_hour) " +
                "VALUE (?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, duration);
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, description);
            preparedStatement.setInt(5, students_count);
            preparedStatement.setDouble(6, price);
            preparedStatement.setDouble(7, price / duration);

            int executeUpdate = preparedStatement.executeUpdate();

            if (executeUpdate > 0) {
                System.out.println("Запись добавлена успешна");
            } else {
                System.out.println("Произошла ошибка");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeThePriceOfTheCourseType(int price, String type) {
        String query = "UPDATE courses set price = ? where type = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, price);
            preparedStatement.setString(2, type);

            int executeUpdate = preparedStatement.executeUpdate();

            if (executeUpdate > 0) {
                System.out.println("Данные успешно изменены");
            } else {
                System.out.println("Произошла ошибка");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletingACourseByItsDuration (int duration) {
        String query = "DELETE FROM courses WHERE duration < ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, duration);

            int executeUpdate = preparedStatement.executeUpdate();

            if (executeUpdate > 0) {
                System.out.println("Данные успешно удалены");
            } else {
                System.out.println("Произошла ошибка");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

