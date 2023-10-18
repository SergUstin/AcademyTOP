package company.lesson01;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Loader.loader("C:\\Users\\Serg\\AcademyTOP\\SpringModul\\src\\company\\lesson01\\resources\\numbers1.txt");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                Loader.loader("C:\\Users\\Serg\\AcademyTOP\\SpringModul\\src\\company\\lesson01\\resources\\numbers2.txt");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
