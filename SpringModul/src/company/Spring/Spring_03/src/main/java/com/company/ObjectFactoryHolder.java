package com.company;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class ObjectFactoryHolder {
    private final ObjectFactory<PrototypeComponent> prototypeComponentObjectFactory;
    private final ObjectFactory<FirstSingleton> firstSingletonObjectFactory;

    public ObjectFactoryHolder(ObjectFactory<PrototypeComponent> prototypeComponentObjectFactory, ObjectFactory<FirstSingleton> firstSingletonObjectFactory) {
        this.prototypeComponentObjectFactory = prototypeComponentObjectFactory;
        this.firstSingletonObjectFactory = firstSingletonObjectFactory;
    }

    public FirstSingleton getSingleton() {
        return firstSingletonObjectFactory.getObject();
    }

    public PrototypeComponent getPrototypeComponent() {
        return prototypeComponentObjectFactory.getObject();
    }
}
