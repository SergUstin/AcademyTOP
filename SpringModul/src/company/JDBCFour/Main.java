package company.JDBCFour;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowSet = factory.createCachedRowSet();
            rowSet.setCommand("SELECT * FROM courses");
            rowSet.execute(connection);

            while (rowSet.next()) {
                if (rowSet.getString("type").equals("DESIGN")) {
                    rowSet.updateInt("duration", 50);
                    rowSet.updateRow();
                }
                System.out.println(rowSet.getString("type") + " | " + rowSet.getInt("duration"));
            }


            rowSet.close();




//            CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
//            rowSet.setUrl(url);
//            rowSet.setUsername(user);
//            rowSet.setPassword(pass);
//
//            String selectSQL = "SELECT * FROM courses";
//            rowSet.setCommand(selectSQL);
//            rowSet.execute(connection);
//
//            System.out.println("Initial Data: ");
//            while (rowSet.next()) {
//                int courseId = rowSet.getInt(1);
//                String courseName = rowSet.getString(2);
//                int duration = rowSet.getInt(3);
//                String type = rowSet.getString(4);
//                String description = rowSet.getString(5);
//                int teacherId = rowSet.getInt(6);
//                int studentsCount = rowSet.getInt(7);
//                int price = rowSet.getInt(8);
//                float pricePerHour = rowSet.getFloat(9);
//
//                System.out.println("Course ID: " + courseId);
//                System.out.println("Course name: " + courseName);
//                System.out.println("Duration: " + duration);
//                System.out.println("Type: " + type);
//                System.out.println("Description: " + description);
//                System.out.println("Teacher ID: " + teacherId);
//                System.out.println("Students Count: " + studentsCount);
//                System.out.println("Price: " + price);
//                System.out.println("Price_per_hour: " + pricePerHour);
//                System.out.println();
//            }


//            RowSetFactory factory = RowSetProvider.newFactory();
//            CachedRowSet rowSet = factory.createCachedRowSet();
//            rowSet.setCommand("SELECT * FROM courses");
//            rowSet.execute(connection);
//
//            while (rowSet.next()) {
//                String courseName = rowSet.getString("name");
//                int duration = rowSet.getInt("duration");
//                String courseType = rowSet.getString("type");
//
//                System.out.println(courseName + " | " + duration + " | " + courseType);
//            }
//
//            rowSet.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
