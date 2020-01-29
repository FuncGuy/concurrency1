public class StringBenchMark {

   private static volatile String capture;

    public static void main(String[] args) {

        for(int i = 1 ; i< 1_000_000; i*=2){

            long time = System.currentTimeMillis();

            capture = appendPlusEquals(i);
            time = System.currentTimeMillis() - time;
            System.out.println("+= "+ time + "ms");
            time = System.currentTimeMillis();
            capture = appendAppend(i);
            time = System.currentTimeMillis() - time;
            System.out.println("SB "+ time + "ms");
        }
    }

    private static String appendAppend(int i) {
        StringBuilder sb = new StringBuilder();
        for(int j=0;j<i;j++){
            sb.append(j);
        }
        return sb.toString();
    }

    private static String appendPlusEquals(int i) {
        String s = "";
        for(int j=0; j< i;j++){
            s+=j;
        }
        return s;
    }
}
