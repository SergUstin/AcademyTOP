package StudentRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class AddSubjectAndGrade {
    public static Map<String, Integer> magazine = new HashMap<>();
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static boolean exit = false;

    public static void addSubjectAndGrade() throws IOException {
        while (!exit) {
            System.out.print("Введите название предмета: ");
            String subject = reader.readLine();
            System.out.print("Введите оценку: ");
            int grade = Integer.parseInt(reader.readLine());
            if (grade < 2 || grade > 5) {
                System.out.println("Вы ввели неверное значение!");
            } else {
                magazine.put(subject, grade);
            }
            System.out.print("Ввести еще один предмет? Да / Нет ");
            if (reader.readLine().equalsIgnoreCase("Да")) {
                addSubjectAndGrade();
            } else {
                exit = true;
            }
        }
    }

}
