package com.xiaoshu.file;

import cn.myzf.common.MessageFile;
import cn.myzf.server.config.MessageType;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.*;
import java.util.Date;

/**
 * 功能说明： 对protobuf类进行测试；
 *
 * @ com.xiaoshu.file
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/25@11:08
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class MessageFileProtobufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        MessageFile.Message.Builder builder = MessageFile.Message.newBuilder();
        // 设置基础信息
        builder.setCid("15646481133-000000");
        builder.setGid(String.valueOf(16464813587931313L));
        builder.setCreatedAt(new Date().getTime());
        builder.setFrom("4");
        builder.setTo("6");
        builder.setType(MessageFile.Message.Type.PRIVATE);
        byte[] file = getBytesByFile("D:/temp/11zzz.png");
        // 设置body信息
        MessageFile.File.Builder fileContentBuilder = MessageFile.File.newBuilder();
        fileContentBuilder.setData(ByteString.copyFrom(file));
        fileContentBuilder.setSize(1024);
        fileContentBuilder.setPrivate(true);
        fileContentBuilder.setType("private");
        // 构造消息服务
        MessageFile.MessageContent.Builder contentBuilder = MessageFile.MessageContent.newBuilder();
        contentBuilder.setContentType(MessageType.FILE);
        contentBuilder.setContent(Any.pack(fileContentBuilder.build()));
        builder.setMessageContent(contentBuilder.build());
        MessageFile.Message message = builder.build();
        // 消息构造完成；

        MessageFile.File fileObject = message.getMessageContent().getContent().unpack(MessageFile.File.class);

        System.out.println(fileObject);
        readBin2Image(fileObject.getData().toByteArray(), "D:/temp/11zzz" + System.currentTimeMillis() + ".png");
        System.out.println("转换完成");

    }

    //将文件转换成Byte数组
    public static byte[] getBytesByFile(String pathStr) {
        File file = new File(pathStr);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 将byte[] 转换为 file
    public static void readBin2Image(byte[] byteArray, String targetPath) {
        InputStream in = new ByteArrayInputStream(byteArray);
        File file = new File(targetPath);
        String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
        if (!file.exists()) {
            new File(path).mkdir();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
