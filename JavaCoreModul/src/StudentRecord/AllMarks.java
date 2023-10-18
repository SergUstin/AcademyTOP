package StudentRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllMarks {
    public static void allMarks() {
        List<Map.Entry<String, Integer>> marksList = new ArrayList<>(AddSubjectAndGrade.magazine.entrySet());
        System.out.println("Все оценки по предметам: " + marksList);
        System.out.println();
    }
}
