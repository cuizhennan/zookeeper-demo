package xyz.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * Created by mx on 16/2/21.
 */
public class Zookeeper_Constructor_Usage_Simple implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);


    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            connectedSemaphore.countDown();
        }
    }


    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Zookeeper_Constructor_Usage_Simple());

        System.out.println(zooKeeper.getState());

        try {
            connectedSemaphore.await();
        } catch (InterruptedException e) {
        }

        System.out.println("Zookeeper session established.");
    }
}
