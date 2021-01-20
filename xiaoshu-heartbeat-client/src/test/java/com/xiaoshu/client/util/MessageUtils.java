package com.xiaoshu.client.util;

import com.xiaoshu.client.model.MyMessage;

import java.util.UUID;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.client.util
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/19@17:39
 * <p>
 * Copyright (C)2012-@2021 深圳优必选科技 All rights reserved.
 */
public class MessageUtils {

    /**
     * 构建发送数据；
     *
     * @param clientId
     * @param data
     * @return
     */
    public static MyMessage createMyMessage(String clientId, String data) {
        MyMessage message = new MyMessage();
        message.setMessageId(UUID.randomUUID().toString().replaceAll("_", ""));
        message.setData(data);
        message.setClientId(clientId);
        return message;
    }
}
