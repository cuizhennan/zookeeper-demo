package xyz.demo.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by mx on 16/2/21.
 */
public class Boss implements Runnable {

    private CountDownLatch countDownLatch;

    public Boss(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        System.out.println("老板正在等所有的工人干完活......");

        try {
            countDownLatch.await();
        } catch (InterruptedException ie) {
        }

        System.out.println("工人活都干完了，老板开始检查了！");
    }
}
