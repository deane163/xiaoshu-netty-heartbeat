package com.xiaoshu.client.model;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.client.model
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/19@17:18
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public enum MyCommandType {

    /**
     * 验证
     */
    AUTH(1),
    /**
     * ping
     */
    PING(2),
    /**
     * pong
     */
    PONG(3),
    /**
     * 上传数据
     */
    UPLOAD_DATA(4),
    /**
     * 推送数据
     */
    PUSH_DATA(5),

    /**
     * 验证返回
     */
    AUTH_BACK(11),

    UPLOAD_DATA_BACK(14),

    PUSH_DATA_BACK(15);

    int type;

    MyCommandType(int type) {
        this.type = type;
    }
}
