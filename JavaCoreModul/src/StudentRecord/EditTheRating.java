package StudentRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class EditTheRating {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void editTheRating() throws IOException {
        System.out.print("Введите название предмета: ");
        String subject = reader.readLine();
        System.out.print("Введите новую оценку: ");
        int newGrade = Integer.parseInt(reader.readLine());
        for (Map.Entry<String, Integer> entry : AddSubjectAndGrade.magazine.entrySet()) {
            if ((newGrade < 2 || newGrade > 5)) {
                System.out.println("Вы ввели не верную оценку!");
                break;
            } else {
                AddSubjectAndGrade.magazine.replace(subject, entry.getValue(), newGrade);
            }
        }
    }
}
