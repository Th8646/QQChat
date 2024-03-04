package com.wpt.qqclient.service;/**
 * @author wpt@onlying.cn
 * @date 2024/2/21 16:26
 */

import com.wpt.qqcommon.Message;
import com.wpt.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @projectName: QQClient
 * @package: com.wpt.qqclient.service
 * @className: MessageClientService
 * @author: wpt
 * @description: 提供消息相关的服务方法
 * @date: 2024/2/21 16:26
 * @version: 1.0
 */
public class MessageClientService {
    /**
     * @param content  内容
     * @param senderId 消息发送者Id
     * @param getterId 消息接收者Id
     */
    public void sendMessageToOne(String content, String senderId, String getterId) {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMN_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setContent(content);
        message.setSendTime(new java.util.Date().toString());//发送时间
        System.out.println(senderId + "对" + getterId + "说：" + content);

        //发送到服务端
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.
                    getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToAll(String content, String senderId) {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
        message.setSender(senderId);
        message.setContent(content);
        message.setSendTime(new java.util.Date().toString());//发送时间
        System.out.println(senderId + "对大家说：" + content);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.
                    getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
