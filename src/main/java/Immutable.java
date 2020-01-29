import java.util.ArrayList;

public final class Immutable {

    private int id;
    private ArrayList<Integer> integerList = new java.util.ArrayList<>();

    public Immutable(int id) {
        this.id = id;
        integerList.add(1);
    }

    public int getId(){
        return id;
    }

    public ArrayList<Integer> getIntegerList() {
        return (ArrayList)integerList.clone();
    }

    public static void main(String[] args) {

        Immutable immutable = new Immutable(1);

        immutable.getIntegerList().add(2);

        System.out.println(immutable.getIntegerList());


    }

}



