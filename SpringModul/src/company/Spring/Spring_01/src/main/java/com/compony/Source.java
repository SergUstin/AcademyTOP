package company.Spring.Spring_01.src.main.java.com.compony;

public class Source {

    @Inject
    private Injectable injectable;

    public void call() {
        System.out.println("Source calling call...");
        injectable.doWork();
    }
}
