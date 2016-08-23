package xyz.demo;

import org.apache.zookeeper.*;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mx on 16/2/21.
 */
public class Zookeeper_Create_API_Sync_Usage implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 5000, new Zookeeper_Create_API_Sync_Usage());

        countDownLatch.await();

        String path1 = zooKeeper.create("/test", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("path1 " + path1);

        String path2 = zooKeeper.create("/myfirstNode", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("path2 " + path2);
    }

    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }
}
