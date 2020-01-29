package DesignPatterns;

import java.io.Serializable;

public class SecuirtyModule implements Serializable {

    private static SecuirtyModule instance = new SecuirtyModule();

    private final int meaningOfLife = 42;

    private SecuirtyModule() {

    }

    public static SecuirtyModule getInstance(){
        return instance;
    }

    public void login(String heinz, String fkdsfldsk) {

    }

    private Object readResolve() {
        return instance;
    }
}
