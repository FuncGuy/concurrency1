import java.util.LinkedHashSet;
import java.util.Set;

public class MyHashSet<T> {

    public static void main(String[] args) {

        Set<String> set = new LinkedHashSet<>();
        set.add(null);

    }

}

class Expensive {
    private final String SECRET = "SECRET";
    private Expensive(){}
}