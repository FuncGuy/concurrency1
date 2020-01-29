import java.lang.reflect.Field;

public class HackingEnums {

    public static void main(String... args) {

        Field[] declaredFields = String.class.getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++) {

            System.out.println(declaredFields[i]);
        }
    }
}
