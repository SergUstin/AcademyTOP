package company.JDBCExp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/top";
        String user = "root";
        String pass = "root";

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement()) {

//            statement.execute("UPDATE courses SET name = 'Веб-разработчик не с нуля' WHERE id = 1");

//            String query = "INSERT INTO courses (name) VALUES ('Администрирование баз данных')";

//            statement.executeUpdate(query);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String courseName = resultSet.getString("name");
                int duration = resultSet.getInt("duration");
                String type = resultSet.getString("type");
                String description = resultSet.getString("description");
                int teacher_id = resultSet.getInt("teacher_id");
                int students_count = resultSet.getInt("students_count");
                int price = resultSet.getInt("price");
                int price_per_hour = resultSet.getInt("price_per_hour");

                System.out.println(id + "->" + courseName + "->" + duration + "->" +
                        type + "->" + description + "->" + teacher_id + "->" +
                        students_count + "->" + price + "->" + price_per_hour);
            }

            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
