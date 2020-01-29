import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class WeakReference {

    public static void main(String[] args) {

        List<String> urls = new ArrayList<>();
        urls.add("https://www.google.com");
        urls.add("https://www.amazon.com");
        urls.add("https://www.lenovo.com");
        urls.add("https://www.educative.io");
        urls.add("https://www.highscalability.com");

        supplyAsync(() -> GetContent.getcontent(urls), newFixedThreadPool(8))
                .thenCompose(n -> supplyAsync(() -> GetContent.toLowercase(n)))
                 .exceptionally(WeakReference::apply)
                .thenAcceptAsync(n -> System.out.println(n.join()), newFixedThreadPool(8));
    }

    private static CompletableFuture<String> apply(Throwable e) {
        System.out.println(e.getMessage());
        return null;
    }
}
