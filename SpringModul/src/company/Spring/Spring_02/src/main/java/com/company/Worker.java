package company.Spring.Spring_02.src.main.java.com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Worker {

    private LogicClass simpleLogic;

    private LogicClass dataSimpleLogic;

    private DataComponent dataComponent;

    @Autowired
    public Worker(LogicClass simpleLogicClass, LogicClass logicClassData, DataComponent dataComponent) {
        this.simpleLogic = simpleLogicClass;
        this.dataSimpleLogic = logicClassData;
        this.dataComponent = dataComponent;
    }

    public void call() {
        simpleLogic.simpleLogic();
        dataSimpleLogic.printLogicData();
        dataComponent.someWork();
    }
}
