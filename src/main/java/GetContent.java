import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetContent {

    private static BufferedReader br = null;
    private static StringBuilder sb = new StringBuilder();

    public static StringBuilder getcontent(List<String> urll) {

        urll.forEach(su -> {

            try {

                URL url = new URL(su);

                br = new BufferedReader(new InputStreamReader(url.openStream()));

                String line;


                while ((line = br.readLine()) != null) {

                    sb.append(line);
                    sb.append(System.lineSeparator());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                BufferedReader br = null;
            }
        });
        return sb;
    }

    public static CompletableFuture<String> toLowercase(StringBuilder stringBuilder){
       return CompletableFuture.completedFuture(stringBuilder.toString().toUpperCase());
    }
}
