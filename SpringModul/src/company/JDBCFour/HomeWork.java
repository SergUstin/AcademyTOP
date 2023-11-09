package company.JDBCFour;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HomeWork {
    static String url = "jdbc:mysql://localhost:3306/top";
    static String user = "root";
    static String pass = "root";

    public static void main(String[] args) {
        System.out.println("Вывод информации о курсах");
        informationAboutCourses();
        System.out.println();

        System.out.println("Добавление нового курса");
        addNewCourses("Scrum", 100);
        System.out.println();

        System.out.println("Обновление продолжительности курса");
        updateDurationCourses(10, "Scrum");
        System.out.println();

        System.out.println("Вывод информации о студентах");
        getInformationAboutStudent("Мобильный разработчик с нуля");
        System.out.println();

        System.out.println("Добавление нового студента");
        addNewStudent("Устин Сергей", 31, "Java-разработчик");



    }

    public static void informationAboutCourses () {
        String sql = "SELECT * FROM courses";

        try {
            CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
            rowSet.setUrl(url);
            rowSet.setUsername(user);
            rowSet.setPassword(pass);
            rowSet.setCommand(sql);
            rowSet.execute();

            while (rowSet.next()) {
                System.out.println(rowSet.getString("name") + ": " + rowSet.getInt("duration"));
            }

            rowSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addNewCourses(String courseName, int duration) {
        String sql = "SELECT * FROM courses";

        try {

            // Подключаемся к базе данных
            CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
            rowSet.setUrl(url);
            rowSet.setUsername(user);
            rowSet.setPassword(pass);
            rowSet.setCommand(sql);
            rowSet.execute();

            // Этот тип операции может изменить только автономный RowSet
            rowSet.moveToInsertRow(); // Сначала перемещаем указатель на пустую (новую) строку, текущая позиция запоминается
            rowSet.updateString(2, courseName); // Выбираем столбец в таблице и присваиваем значение
            rowSet.updateInt(3, duration);// Выбираем столбец в таблице и присваиваем значение
            rowSet.insertRow(); // Добавляем текущую (новую) строку к остальные строкам
            rowSet.moveToCurrentRow(); // Возвращаем указатель на ту строку, где он был до вставки

            System.out.println("Данные успешно изменены в кеш!");

            // А теперь мы можем все наши изменения залить в базу
            Connection connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false); // Нужно для синхронизации
            rowSet.acceptChanges(connection); // Синхронизировать данные в базу данных

            System.out.println("Данные успешно изменены в БД!");

            // Закрываем ресурсы
            connection.close();
            rowSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateDurationCourses(int duration, String courseName) {
        String sqlUpdate = "UPDATE courses set duration = ? where courses.name = ?";

        try {

            // Подключаемся к базе данных
            CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
            rowSet.setUrl(url);
            rowSet.setUsername(user);
            rowSet.setPassword(pass);

            // Этот тип операции может изменить только автономный RowSet
            rowSet.setCommand(sqlUpdate); // Передаем запрос
            rowSet.setInt(1, duration); // Вносим изменения
            rowSet.setString(2, courseName); // Вносим изменения
            rowSet.execute(); // Выполняем команды

            System.out.println("Данные успешно изменены в кеш!");

            // А теперь мы можем все наши изменения залить в базу
            Connection connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false); // Нужно для синхронизации
            rowSet.acceptChanges(connection); // Синхронизировать данные в базу данных

            System.out.println("Данные успешно изменены в БД!");

            // Закрываем ресурсы
            connection.close();
            rowSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void getInformationAboutStudent(String courseName) {
        String sql = "SELECT students.name, students.age FROM students JOIN purchaselist ON students.name = purchaselist.student_name WHERE course_name = ?";

        try {

            // Подключаемся к базе данных
            CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
            rowSet.setUrl(url);
            rowSet.setUsername(user);
            rowSet.setPassword(pass);

            // Этот тип операции может изменить только автономный RowSet
            rowSet.setCommand(sql); // Передаем запрос
            rowSet.setString(1, courseName); // Вносим название курса
            rowSet.execute(); // Выполняем команды

            while (rowSet.next()) {
                System.out.println(rowSet.getString("name") + ": " + rowSet.getInt("age"));
            }

            rowSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addNewStudent(String studentName, int studentAge, String courseName) {
        String sqlStudent = "SELECT * FROM students";

        try {

            // Подключаемся к базе данных
            CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
            rowSet.setUrl(url);
            rowSet.setUsername(user);
            rowSet.setPassword(pass);
            rowSet.setCommand(sqlStudent);
            rowSet.execute();

            // Этот тип операции может изменить только автономный RowSet
            rowSet.moveToInsertRow(); // Сначала перемещаем указатель на пустую (новую) строку, текущая позиция запоминается
            rowSet.updateString(2, studentName); // Выбираем столбец в таблице и присваиваем значение
            rowSet.updateInt(3, studentAge);// Выбираем столбец в таблице и присваиваем значение
            rowSet.insertRow(); // Добавляем текущую (новую) строку к остальные строкам
            rowSet.moveToCurrentRow(); // Возвращаем указатель на ту строку, где он был до вставки

            System.out.println("Данные успешно изменены в кеш!");

            // А теперь мы можем все наши изменения залить в базу
            Connection connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false); // Нужно для синхронизации
            rowSet.acceptChanges(connection); // Синхронизировать данные в базу данных

            System.out.println("Данные успешно изменены в БД!");

            // Закрываем ресурсы
            connection.close();
            rowSet.close();

            addValueOnSecondTable(studentName, courseName);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void addValueOnSecondTable(String studentName, String courseName) {
        String sqlPurchaselist = "SELECT * FROM purchaselist";

        try {

            // Подключаемся к базе данных
            CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
            rowSet.setUrl(url);
            rowSet.setUsername(user);
            rowSet.setPassword(pass);
            rowSet.setCommand(sqlPurchaselist);
            rowSet.execute();

            // Этот тип операции может изменить только автономный RowSet
            rowSet.moveToInsertRow(); // Сначала перемещаем указатель на пустую (новую) строку, текущая позиция запоминается
            rowSet.updateString(1, studentName); // Выбираем столбец в таблице и присваиваем значение
            rowSet.updateString(2, courseName);// Выбираем столбец в таблице и присваиваем значение
            rowSet.insertRow(); // Добавляем текущую (новую) строку к остальные строкам
            rowSet.moveToCurrentRow(); // Возвращаем указатель на ту строку, где он был до вставки

            System.out.println("Данные успешно изменены в кеш!");

            // А теперь мы можем все наши изменения залить в базу
            Connection connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false); // Нужно для синхронизации
            rowSet.acceptChanges(connection); // Синхронизировать данные в базу данных

            System.out.println("Данные успешно изменены в БД!");

            // Закрываем ресурсы
            connection.close();
            rowSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
