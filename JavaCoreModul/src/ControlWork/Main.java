package ControlWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // Первое задание
        System.out.print("Введите r - ");
        double r = Double.parseDouble(reader.readLine());
        double S = Math.PI * Math.pow(r, 2);
        System.out.println("Площадь окружности = " + S);

        // Реализация второго задания
        int[] arr = {1, 2, 3, 4, 5};
        System.out.print("Результат сдвига: ");
        System.out.println(Arrays.toString(moveRightArray(arr, 2)));
        System.out.println();

        // Реализация третьего задания
        Triangle triangle = new Triangle(9, 10, 5);
        triangle.getPerimeter();
        triangle.getArea();
    }

    // Метод второго задания
    public static int[] moveRightArray (int[] array, int n) {
        if (array != null) {
            int length = array.length;
            int[] b = new int[length];

            System.arraycopy(array, n, b, 0, length - n);
            System.arraycopy(array, 0, b, length - n, n);
            return b;
        } else {
            return null;
        }
    }
}
