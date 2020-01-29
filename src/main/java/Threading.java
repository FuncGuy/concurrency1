public class Threading {

    private static final Object LOCK = new Object();
    private static Object myObj = null;

    public static Object retrieve() {
        if(myObj == null) {
            synchronized (LOCK){
                myObj = create();
            }
        }

        return myObj;
    }

    private static Object create() {

        return new Object();
    }
}
