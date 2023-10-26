package company.Car_Number;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

//В данной оптимизированной версии класса были внесены следующие изменения:

//        1. Созданы отдельные экземпляры StringBuilder для номера и кода региона,
//           чтобы их не создавать заново на каждой итерации цикла.

//        2. Создан единый экземпляр StringBuilder для построения строки, которая записывается в файл.
//           После записи строки в файл, его содержимое очищается методом builder.setLength(0).

//        3. В методе padNumber, вместо возврата StringBuilder, передается ссылка на экземпляр StringBuilder,
//           чтобы избежать создания нового StringBuilder на каждой итерации.

//        4. В методе padNumber используется метод setLength(0) перед заполнением числа нулями,
//           чтобы очистить содержимое StringBuilder.

//        В результате этих оптимизаций, класс Loader будет работать более эффективно и использовать меньше ресурсов и скорость в 160 ms.

public class Loader {
    public static void loader(String location) throws FileNotFoundException {
        long start = System.currentTimeMillis();

        try (PrintWriter writer = new PrintWriter(location)) {
            char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            StringBuilder builder = new StringBuilder();
            StringBuilder numberBuilder = new StringBuilder();
            StringBuilder regionCodeBuilder = new StringBuilder();

            for (int number = 1; number < 1000; number++) {
                padNumber(number, 3, numberBuilder);
                int regionCode = 78;
                padNumber(regionCode, 2, regionCodeBuilder);

                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter)
                                    .append(numberBuilder)
                                    .append(secondLetter)
                                    .append(thirdLetter)
                                    .append(regionCodeBuilder)
                                    .append("\n");
                        }
                        writer.write(builder.toString());
                        builder.setLength(0);
                    }
                }
            }
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static void padNumber(int number, int numberLength, StringBuilder builder) {
        builder.setLength(0);
        builder.append(number);

        int padSize = numberLength - builder.length();
        for (int i = 0; i < padSize; i++) {
            builder.append('0');
        }
    }
}
