package com.wpt.qqcommon;/**
 * @author wpt@onlying.cn
 * @date 2024/1/30 21:35
 */

import java.io.Serializable;

/**
 * @projectName: QQServer
 * @package: com.wpt.qqcommon
 * @className: Message
 * @author: wpt
 * @description: 表示客户端和服务器端通信的消息对象
 * @date: 2024/1/30 21:35
 * @version: 1.0
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sender;  //消息发送者
    private String getter;  //消息接受者
    private String content; //消息内容
    private String sendTime;    //发送时间
    private String mesType;// 在接口中定义消息类型

    //文件相关的字段拓展
    private byte[] fileBytes;
    private int fileLen = 0;
    private String dset;//文件保存路径
    private String src;//文件原路径

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public String getDset() {
        return dset;
    }

    public void setDset(String dset) {
        this.dset = dset;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    public Message(String sender, String getter, String content, String sendTime) {
        this.sender = sender;
        this.getter = getter;
        this.content = content;
        this.sendTime = sendTime;
    }

    public Message() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
