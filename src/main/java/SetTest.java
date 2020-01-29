import java.util.HashSet;
import java.util.Set;

public class SetTest {

    public static void main(String[] args) {

        Set<String> set = new HashSet<>();

        System.out.println(set.add("hello"));
        System.out.println(set.add("hello"));
    }
}
