package datatime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Test2 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Callable<LocalDate> callable = () -> LocalDate.parse("2019-02-05", dateTimeFormatter);

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> resultDate = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            resultDate.add(threadPool.submit(callable));
        }

        for (Future<LocalDate> dateFuture : resultDate) {
            System.out.println(dateFuture.get());
        }

        threadPool.shutdown();
    }
}
