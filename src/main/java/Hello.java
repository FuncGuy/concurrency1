import static java.util.Arrays.asList;

public class Hello {

    public static void main(String... args) {

        asList(new Student(1, "Heinz"),
                new Student(2, "Tony Morris"),
                new Student(3, "Brain"),
                new Student(3, "Brain")
                , new Student(4, "Venkat"))
                .stream()
                .distinct()
                .forEach(stu -> System.out.println(stu.getName()));


    }

}

class Student {

    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

}