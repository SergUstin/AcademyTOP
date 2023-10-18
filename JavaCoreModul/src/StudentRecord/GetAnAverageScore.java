package StudentRecord;

import java.util.Map;

public class GetAnAverageScore {

    public static void getAverageMark() {
        int sum = 0;
        for (Map.Entry<String, Integer> entry : AddSubjectAndGrade.magazine.entrySet()) {
            sum += entry.getValue();
        }
        double averageMark = (double) sum / AddSubjectAndGrade.magazine.size();
        System.out.println("Средний бал: " + averageMark);
        System.out.println();
    }





}
