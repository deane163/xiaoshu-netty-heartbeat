package com.xiaoshu.client.model;

import lombok.Data;
import org.msgpack.annotation.Message;

import java.io.Serializable;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.client.model
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/19@17:22
 * <p>
 * Copyright (C)2012-@2021 深圳优必选科技 All rights reserved.
 */
@Message
@Data
public class MyMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID；
     */
    String clientId;

    /**
     * 唯一消息ID
     */
    String messageId;

    /**
     * 具体传输的数据信息；
     */
    String data;
}
