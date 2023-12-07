package company.Spring.Spring_02.src.main.java.com.company;

import java.text.MessageFormat;

public class LogicClass {
    private String name;
    private int code;

    public LogicClass(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public LogicClass() {
        System.out.println("LogicClass was initialize");
    }

    public void simpleLogic() {
        System.out.println("Something text ");
    }

    public void printLogicData() {
        System.out.println(MessageFormat.format("Simple logic data: {0}, {1}", name, code));
    }
}
