public class DesignPatterns {
    private static DesignPatterns ourInstance = new DesignPatterns();

    public static DesignPatterns getInstance() {
        return ourInstance;
    }

    private DesignPatterns() {
    }
}
