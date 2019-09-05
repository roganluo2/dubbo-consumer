package com.my.rpc.mutex;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MutexDemo {

    static    String CONNECT_STR = "120.79.226.150:2181";


    public CuratorFramework getCuratorFramework()
    {
        CuratorFramework curatorDemo = CuratorFrameworkFactory.builder().connectString(CONNECT_STR).sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        curatorDemo.start();
        return curatorDemo;
    }


    public static void main(String[] args) throws IOException {
        CuratorFramework client = new MutexDemo().getCuratorFramework();
        SelectorClient2 sc = new SelectorClient2(client, "/leader", "ClientB");
        sc.start();
        int read = System.in.read();

    }

    void mutex() throws IOException {
        CuratorFramework curatorFramework = new MutexDemo().getCuratorFramework();
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/lock");
        for(int i =0 ;i<10;i++)
        {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "尝试获取锁");
                try {
                    lock.acquire();
                    System.out.println(Thread.currentThread().getName() + "获得了锁");
                    TimeUnit.SECONDS.sleep(2);
                    lock.release();
                    System.out.println(Thread.currentThread().getName() + "释放了锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.in.read();
    }

}
