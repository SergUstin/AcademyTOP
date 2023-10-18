package company.lesson01;

public class Concatenation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        String str = "";
        for (int i = 0; i < 20_000; i++) {
            str.concat("some text some text some text");
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");

    }
}
