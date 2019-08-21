package datatime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class Test1 {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        Callable<Date> callable = () -> sdf.parse("2019-2-5");
        Callable<Date> callable = () -> DateForatThreadLocal.convert("2019-2-5");

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        List<Future<Date>> resultDate = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            resultDate.add(threadPool.submit(callable));
        }

        for (Future<Date> dateFuture : resultDate) {
            System.out.println(dateFuture.get());
        }

        threadPool.shutdown();
    }
}
