package StudentRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Delete {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void removeSubject() throws IOException {
        System.out.print("Введите название предмета: ");
        String subject = reader.readLine();
        AddSubjectAndGrade.magazine.entrySet().removeIf(entry -> entry.getKey().equalsIgnoreCase(subject));
        System.out.println("Предмет удален! \n");
    }
}
