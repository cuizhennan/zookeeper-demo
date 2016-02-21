package xyz.demo.countdownlatch;

import sun.util.resources.CurrencyNames;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by mx on 16/2/21.
 */
public class Worker implements Runnable {

    private CountDownLatch countDownLatch;

    private String name;

    public Worker(CountDownLatch countDownLatch, String name) {
        this.countDownLatch = countDownLatch;
        this.name = name;
    }

    public void run() {
        this.doTask();
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException ie) {
        }

        System.out.println(this.name + " 活干完了-" + countDownLatch.getCount());
        this.countDownLatch.countDown();
    }

    private void doTask() {
        System.out.println(this.name + " 正在干活!");
    }
}
