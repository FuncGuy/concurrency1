import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Arrays.asList;

public class COWIterationExamples {

    public static void main(String[] args) {

        List<String> names = new CopyOnWriteArrayList<>(
                asList("John", "Anton", "Heinz"));

        Collections.sort(names);

        for(Iterator<String> it = names.iterator(); it.hasNext();) {
            String name = it.next();
            System.out.println("Checking: " + name);
            if(name.contains("o")) names.remove(name);
        }

        System.out.println("names = " + names);
    }
}
