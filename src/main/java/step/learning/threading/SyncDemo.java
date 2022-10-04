package step.learning.threading;

import step.learning.ConsoleColors;
import step.learning.annotations.DemoClass;
import step.learning.annotations.EntryPoint;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

@DemoClass
public class SyncDemo {
    /**
     * counts the sum of year inflation
     */
    @EntryPoint
    public void demo() {
        var pool = Executors.newCachedThreadPool();
//        for (int i = 0; i < months; ++i) {
//            int finalI = i;
//            pool.submit((new Runnable() {
//                @Override
//                public void run() {
//                    synchronized (locker){
//                        sum*=1.10;
//                    }
//                    System.out.printf("Month %d Sum - %d", finalI,sum);
//                }
//            }));
//        }
        sum = 100;
        for (int i = 0; i < months; i++) {
            int finalI1 = i + 1;
            pool.submit((new Runnable() {
                double tmp;

                @Override
                public void run() {
                    synchronized (locker) {
                        tmp = sum = sum *= 1.10;
                    }
                    System.out.printf("%d month - %f\n", finalI1, tmp);
                }
            }));
        }

        pool.shutdown();
    }

    private int months = 12;
    private int threads;
    private double sum;
    private final Object locker = new Object();

    private void demoSync() {
        System.out.println("Sync demo");
        sum = 100;
        threads = months;
        for (int i = 0; i < months; ++i) {
            new Thread(plus10percentSyncFin).start();
        }

        var pool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; ++i) {
            pool.submit(
                    ((Function<Integer, Runnable>) x ->
                            () -> System.out.printf("pool %d works%n", x)
                    ).apply(i));
        }

        var res = pool.submit(() -> {
            return "Hello world!";
        });
        try {
            var str = res.get();
            System.out.println(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        pool.shutdown();
    }

    private Runnable plus10percent = () -> {
        var tmp = sum;
        tmp *= 1.10;
        System.out.println("current sum: " + tmp);
        sum = tmp;
    };


    private Runnable plus10percentSync = () -> {
        synchronized (locker) {
            var tmp = sum;
            tmp *= 1.10;
            System.out.println("current sum: " + tmp);
            sum = tmp;
        }
    };

    private Runnable plus10percentSync2 = () -> {
        synchronized (locker) {
            sum *= 1.10;
        }
        System.out.println("current sum: " + sum);
    };

    private Runnable plus10percentSyncFin = () -> {
        double tmp;
        boolean isLast;
        synchronized (locker) {
            tmp = sum = sum *= 1.10;
            threads--;
            isLast = threads == 0;
        }

        System.out.println(
                (isLast ? ConsoleColors.BLUE_BOLD_BRIGHT : "")
                        + "current sum: " + tmp
                        + (isLast ? ConsoleColors.GREEN_BOLD_BRIGHT : ""));


    };
}
