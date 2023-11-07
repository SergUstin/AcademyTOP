package company.JDBCThree;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HomeWork {
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
        String query = "SELECT c.name AS course_name, c.type AS type, s.age " +
                "FROM courses c " +
                "JOIN subscriptions sub ON c.id = sub.course_id " +
                "JOIN students s ON sub.student_id = s.id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            // Хранит список курсов с количеством студентов
            Map<String, Integer> map = new HashMap<>();

            // Хранит список курсов с суммой возрастов студентов
            Map<String, Integer> map1 = new HashMap<>();

            // Счетчик курсов для проверки
            int counter = 1;

            // Проходим по таблице
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String type = resultSet.getString("type");
                int age = resultSet.getInt("s.age");

                // Фильтруем только те курсы у которых тип "DESIGN"
                if (type.equals("DESIGN")) {
                    // Заполняем названием курсов и количеством студентов
                    map.put(courseName, map.getOrDefault(courseName, 0) + 1);
                    // Заполняем названием курсов и суммой возрастов студентов
                    map1.put(courseName, map1.getOrDefault(courseName, 0) + age);
                }
            }

            // Создаем map для сортировки и вывода результата
            Map<String, Integer> result = new HashMap<>();

            // Заполняем result правильными данными
            for (String string : map.keySet()) {
                // Получаем общее количество возрастов по каждому курсу
                int totalAge = map.get(string);
                // Получаем сумму возрастов по каждому курсу
                int sum = map1.get(string);

                // Получаем среднее значение
                double averageAge = (double) sum/ totalAge;

                // Приводим наше значение к нужному формату
                int formattedAverage = (int) Math.round(averageAge);

                // Заполняем правильным значением result
                result.put(string, formattedAverage);
            }

//             Сортировка по возрасту
            result = result.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));

            // Выводим отсортированное значение
            System.out.println("Группировка по возрасту");
            for (String string : result.keySet()) {
                System.out.println("Course " + counter + ": " + string + "Average age " + result.get(string));
                counter++;
            }
            System.out.println();


            // Сортировка по названию курса
            result = result.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));

            // Выводим отсортированное значение
            System.out.println("Группировка по названию");
            for (String string : result.keySet()) {
                System.out.println("Course " + counter + ": " + string + "Average age " + result.get(string));
                counter++;
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
