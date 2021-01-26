package com.xiaoshu.file.client.common;


import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.file.client.common
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/26@10:50
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class ChannelServerRepository {

    private static final Map<String, Channel> channelCache = new ConcurrentHashMap<>();

    public static void put(String key, io.netty.channel.Channel value) {
        channelCache.put(key, value);
    }

    public static Channel get(String key) {
        return channelCache.get(key);
    }

    public void remove(String key) {
        channelCache.remove(key);
    }

    public int size() {
        return channelCache.size();
    }
}
