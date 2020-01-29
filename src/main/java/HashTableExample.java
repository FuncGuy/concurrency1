import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class HashTableExample {

    public static class Person{
        private final String name;

        public Person(String name){
            this.name = name;
        }

        public int hashCode(){
            return 1;
        }

        public boolean equals(Object obj){
            if(!(obj instanceof Person)) return false;
            System.out.println("equals(" + ((Person)obj).name + ")");
            return name.equals(((Person)obj).name);
        }
    }

    public static void main(String[] args) {

        Map<Person, Object> map = new Hashtable<>();

        map.put(null, "hello");

        map.putIfAbsent(null, "should not put");

        System.out.println(map);


    }
}
