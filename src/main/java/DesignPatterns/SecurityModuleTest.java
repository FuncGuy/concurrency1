package DesignPatterns;

import java.io.*;

public class SecurityModuleTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("securitymodule")
        );

        out.writeObject(SecuirtyModule.getInstance());
        out.close();

        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("securitymodule")
        );

        SecuirtyModule sm = (SecuirtyModule) in.readObject();

        in.close();

        System.out.println(sm == SecuirtyModule.getInstance());

    }
}
