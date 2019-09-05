package com.my.rpc.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ZkClientDemo {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(ZKDemo.CONNECT_STR, 40000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("event。type" + watchedEvent.getType());
            }
        });

        zooKeeper.create("/watch", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.exists("/watch", true);
        TimeUnit.SECONDS.sleep(1);
        zooKeeper.setData("/watch", "1".getBytes(), -1);
        System.in.read();
    }

}
