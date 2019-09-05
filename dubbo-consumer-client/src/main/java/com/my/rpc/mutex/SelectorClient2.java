package com.my.rpc.mutex;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

public class SelectorClient2 extends LeaderSelectorListenerAdapter implements Cloneable {


    private String name;

    private LeaderSelector leaderSelector;

    public SelectorClient2(CuratorFramework client, String path, String name)
    {
        this.name = name;
        leaderSelector = new LeaderSelector(client, path, this);
        leaderSelector.autoRequeue();

    }


    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
        System.out.println(name +"现在是leader了" );
        System.in.read();
    }


    public void start() {
        leaderSelector.start();
    }
}
