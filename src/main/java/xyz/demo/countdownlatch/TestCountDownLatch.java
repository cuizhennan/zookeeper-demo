package xyz.demo.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mx on 16/2/21.
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CountDownLatch countDownLatch = new CountDownLatch(3);

        Worker worker1 = new Worker(countDownLatch, "张三");
        Worker worker2 = new Worker(countDownLatch, "李四");
        Worker worker3 = new Worker(countDownLatch, "王二");

        Boss boss = new Boss(countDownLatch);
        executorService.execute(worker1);
        executorService.execute(worker2);
        executorService.execute(worker3);
        executorService.execute(boss);

        executorService.shutdown();
    }
}
