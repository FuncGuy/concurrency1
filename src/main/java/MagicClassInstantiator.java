public class MagicClassInstantiator {

    public static void main(String ... args) throws ClassNotFoundException {

        if(args.length != 5){
            System.exit(0);
        }

        String className = args[0];

        String param1 = args[1];

        String param2 = args[2];

        String param3 = args[3];

        String methodName = args[4];

        Class<?> aClass = Class.forName(className);


    }
}
