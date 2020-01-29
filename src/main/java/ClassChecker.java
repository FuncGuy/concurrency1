public class ClassChecker {

    public static void checkConstructorIsNoArgsPublic(Class<?> clazz) {

        try {
            clazz.getConstructor();
        }catch (NoSuchMethodException e){
            throw new IllegalStateException(
                    "Class does not have a no-args public constructor"
            );
        }

    }
}
