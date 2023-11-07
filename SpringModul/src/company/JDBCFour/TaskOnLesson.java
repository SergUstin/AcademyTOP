package company.JDBCFour;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TaskOnLesson {
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
        System.out.println("Вывод информации о курсах");
        informationAboutCourses();
        System.out.println();
//        System.out.println("Добавление нового курса");
//        addNewCourses("Scrum", 100);
//        System.out.println();
        System.out.println("Обновление продолжительности курса");
        updateDurationCourses("Excel", 60);
        System.out.println();
        System.out.println("Вывод информации о студентах");


    }

    public static void informationAboutCourses () {
        try {
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowSet = factory.createCachedRowSet();
            rowSet.setCommand("SELECT * FROM courses");
            rowSet.execute(connection);

            while (rowSet.next()) {
                System.out.println(rowSet.getString("name") + ": " + rowSet.getInt("duration"));
            }


            rowSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addNewCourses(String courseName, int duration) {
        String sql = "INSERT INTO courses (name, duration) VALUE (?, ?)";
        try {
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowSet = factory.createCachedRowSet();
            rowSet.setCommand(sql);
            rowSet.execute(connection);
            rowSet.setReadOnly(false);

            while (rowSet.next()) {
                rowSet.setString(1, courseName);
                rowSet.setInt(2, duration);
                rowSet.execute();
                rowSet.acceptChanges();
            }

            System.out.println("Данные успешно изменены!");

            rowSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateDurationCourses(String courseName, int duration) {
        String sql = "UPDATE courses set duration = ? where courses.name = ?";
        try {
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowSet = factory.createCachedRowSet();
            rowSet.setCommand(sql);
            rowSet.execute(connection);

            rowSet.moveToInsertRow();
            rowSet.updateString("name", courseName);
            rowSet.updateInt("duration", duration);
            rowSet.insertRow();
            rowSet.moveToInsertRow();

            rowSet.beforeFirst();
            while (rowSet.next()) {
                System.out.println(rowSet.getString(1) + " | " + rowSet.getInt(2));
            }

            connection.setAutoCommit(false);
            rowSet.acceptChanges(connection);

            System.out.println("Данные успешно изменены!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void getInformationAboutStudent(String courseName) {
        String sql = "";

    }


}
