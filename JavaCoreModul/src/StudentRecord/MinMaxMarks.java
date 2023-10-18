package StudentRecord;

import java.util.Map;

public class MinMaxMarks {
    public static void minMark() {
        int integer = AddSubjectAndGrade.magazine.values().stream().min(Integer::compare).orElseThrow();
        AddSubjectAndGrade.magazine.entrySet().stream().filter(entry -> integer == entry.getValue())
                .map(entry -> "Минимальная оценка - " + integer + " " + "по предмету - "
                + entry.getKey()).forEach(System.out::println);
    }

    public static void maxMark() {
        int integer = AddSubjectAndGrade.magazine.values().stream().max(Integer::compare).orElseThrow();
        AddSubjectAndGrade.magazine.entrySet().stream().filter(entry -> integer == entry.getValue())
                .map(entry -> "Максимальная оценка - " + integer + " " + "по предмету - "
                + entry.getKey()).forEach(System.out::println);
    }
}
