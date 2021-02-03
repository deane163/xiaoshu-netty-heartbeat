package com.xiaoshu.file.util;


import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * 功能说明：
 *
 * @ com.xiaoshu.file.util
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/26@16:44
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class ZookeeperClientUseCase {


    private ZookeeperClient zookeeperClient = null;

    @Before
    public void before() throws Exception {

        zookeeperClient = new ZookeeperClient("127.0.0.1:2181", 30 * 1000, 3 * 1000, new ExponentialBackoffRetry(1000, 3));
    }

    @Test
    public void testCreate() throws Exception {
        zookeeperClient.createNode("/testzookeeper/names", "flynn1,flynn2,flynn3", CreateMode.PERSISTENT);

    }


    @Test
    public void testCreateEphemeral() throws Exception {
        zookeeperClient.createNode("/testzookeeper/names", "flynn1,flynn2,flynn3", CreateMode.EPHEMERAL);
        zookeeperClient.close();
    }

    @Test
    public void testGet() throws Exception {

        //路径存在时
        String data = zookeeperClient.getNode("/testzookeeper/names").orElseGet(null);
        System.out.println(data);
        System.out.println("------------");

        //当节点不存在时抛出异常
        //KeeperException.NoNodeException
        System.out.println(zookeeperClient.getNode("/testzookeeper/ages").orElse(null));
    }

    @Test
    public void testCheckExists() throws Exception {
        Assert.assertEquals(zookeeperClient.checkExists("/testzookeeper/ages"), false);
        Assert.assertEquals(zookeeperClient.checkExists("/testzookeeper/names"), true);
    }

    @Test
    public void testDelete() throws Exception {
        zookeeperClient.deleteNode("/testzookeeper/names");
    }

    @Test
    public void testChildren() throws Exception {
        System.out.println(zookeeperClient.getChildren("/testzookeeper"));
    }

    @Test
    public void testChilrenData() throws Exception {
        System.out.println(zookeeperClient.getChildrenData("/testzookeeper"));
    }

    @Test
    public void testListener() throws Exception {
        zookeeperClient.addListener("/testzookeeper/names", (TreeCacheEvent event) -> {

            System.out.println("--------------");
            System.out.println(event.getType());
            System.out.println(event.getData().getPath());
            System.out.println(new String(event.getData().getData()));
            System.out.println("^^^^^^^^^^^^^^^");
        });

//      zookeeperClient.setNode("/testzookeeper/names", "chester1,chester2,chester3,cheste");
        zookeeperClient.setNode("/testzookeeper/names", "xxx,ewewe,cerewr");
        zookeeperClient.setNode("/testzookeeper/names/subnames", "dhtia,ehwei,thhe");
        zookeeperClient.setNode("/testzookeeper/names/subnames/xx", "meitian,teiyw");
//      zookeeperClient.deleteNode("/testzookeeper/names/subnames");
        zookeeperClient.deleteNodeChildren("/testzookeeper/names");
        Thread.currentThread().join();
    }

    @Test
    public void testCreateSequNode() throws Exception {
        zookeeperClient.createNode("/testzookeeper/names", "xxx,ewewe,cerewr1", CreateMode.PERSISTENT_SEQUENTIAL);
        zookeeperClient.createNode("/testzookeeper/names", "xxx,ewewe,cerewr2", CreateMode.PERSISTENT_SEQUENTIAL);
        zookeeperClient.createNode("/testzookeeper/names", "xxx,ewewe,cerewr3", CreateMode.PERSISTENT_SEQUENTIAL);
        zookeeperClient.createNode("/testzookeeper/names", "xxx,ewewe,cerewr4", CreateMode.PERSISTENT_SEQUENTIAL);
    }

    @Test
    public void testChildrenData() throws Exception {
        System.out.println(zookeeperClient.getChildrenData("/testzookeeper"));
    }
}
