import java.io.Serializable;
import java.lang.annotation.Annotation;

public class Serial implements Serializable {

    public static void main(String[] args) throws ClassNotFoundException {
        Class aClass = null;
        try {
             aClass = Class.forName("Serial2");
        }catch (Exception e){
            System.out.println(e);
        }
       Annotation[] annotation = aClass.getAnnotations();
        for (Annotation annotation1 : annotation) {
            System.out.println(annotation1.annotationType());
        }

    }
}

class Serial2 extends Serial{

}


