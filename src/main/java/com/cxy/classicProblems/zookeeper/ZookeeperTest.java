/*
package com.cxy.classicProblems.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

*/
/**
 * Created by lidongpeng on 2018/4/27.
 *//*

public class ZookeeperTest {


    void getLock() throws KeeperException, InterruptedException{
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper("10.112.179.149:2181,10.112.179.150:2181,10.112.179.151:2181" ,
                    1000, new Watcher() {
                // 监控所有被触发的事件
                public void process(WatchedEvent event) {
                    System.out.println("已经触发了" + event.getType() + "事件！");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> list = zk.getChildren(root, false);
        String[] nodes = list.toArray(new String[list.size()]);
        Arrays.sort(nodes);
        if(myZnode.equals(root+"/"+nodes[0])){
            doAction();
        }
        else{
            waitForLock(nodes[0]);
        }
    }
    void waitForLock(String lower) throws InterruptedException, KeeperException {
        Stat stat = zk.exists(root + "/" + lower,true);
        if(stat != null){
            mutex.wait();
        }
        else{
            getLock();
        }
    }
}
*/
