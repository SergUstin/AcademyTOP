package company.JDBCTwo;

import java.sql.*;

public class Main {

//    public static void main(String[] args) {
//        String url = "jdbc:mysql://localhost:3306/top";
//        String user = "root";
//        String pass = "root";
//
//        String query = "SELECT course_name,\n" +
//                "       COUNT(course_name)/(MAX(MONTH(subscription_date)) - MIN(MONTH(subscription_date))) AS average_number_of_sales\n" +
//                "FROM PurchaseList\n" +
//                "GROUP BY course_name";
//
//        try (Connection connection = DriverManager.getConnection(url, user, pass);
//             Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery(query);
//
//            System.out.printf("|%-35s | %-10s |\n", "Название курса", "Среднее количество покупок");
//            while (resultSet.next()) {
//                System.out.printf("|%-35s | %-22s |\n",resultSet.getString("course_name"), resultSet.getString("average_number_of_sales"));
//            }
//
//            resultSet.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/top?userName=root&password=root";
        String user = "root";
        String pass = "root";

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            String queryUpdate = "UPDATE courses set price = ? where type = 'PROGRAMMING'";

            try (PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate)) {

                preparedStatement.setInt(1, 5000);

                int executeUpdate = preparedStatement.executeUpdate();

                if (executeUpdate > 0) {
                    System.out.println("Запись добавлена успешна");
                } else {
                    System.out.println("Произошла ошибка");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

