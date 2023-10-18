package StudentRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Заполните журнал студента.");
        AddSubjectAndGrade.addSubjectAndGrade();
        System.out.println();

        while (true) {
            System.out.println("""
                    Выберете действие введя его номер: \s
                    1 - получить средний балл \s
                    2 - вывести все оценки \s
                    3 - вывести максимальную оценку и название предмета \s
                    4 - вывести минимальнуою оценку и назавание предмета \s
                    5 - редактировать оценку \s
                    6 - удалить предмет
                    7 - выход
                            """);

            String answer = reader.readLine();

            switch (answer) {
                case "1" -> GetAnAverageScore.getAverageMark();
                case "2" -> AllMarks.allMarks();
                case "3" -> MinMaxMarks.maxMark();
                case "4" -> MinMaxMarks.minMark();
                case "5" -> EditTheRating.editTheRating();
                case "6" -> Delete.removeSubject();
                case "7" -> {
                    return;
                }
            }
        }
    }
}
