public class Test {

    public static void main(String[] args) {

        String str = "This is my country india and here i work as software engineer";

        String [] tokens = str.split(" ");

        int maxEven = 0;

        for(int i = 0; i< tokens.length; i++){

                if(tokens[i].length() %2 != 0){
                    maxEven = tokens[i].length();

               for(int j= i+1; j< tokens.length; j++) {
                   if (tokens[j].length() % 2 != 0 && tokens[j].length() > maxEven) {
                       maxEven = tokens[j].length();
                   }
               }
            }
        }

        System.out.println(maxEven);
    }
}
