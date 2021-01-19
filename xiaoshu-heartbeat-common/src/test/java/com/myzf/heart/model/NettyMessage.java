package com.myzf.heart.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：
 *
 * @ com.myzf.heart.model
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/18@20:36
 * <p>
 * Copyright (C)2012-@2021 深圳优必选科技 All rights reserved.
 */
@Data
public  class NettyMessage<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T data;

    private int code;

    private int message;

}
