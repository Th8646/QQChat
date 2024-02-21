package com.wpt.qqclient.service;/**
 * @author wpt@onlying.cn
 * @date 2024/2/2 20:05
 */

import com.wpt.qqcommon.Message;
import com.wpt.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @projectName: QQClient
 * @package: com.wpt.qqcommon.qqclient.service
 * @className: ClientConnectServerThread
 * @author: wpt
 * @description: TODO
 * @date: 2024/2/2 20:05
 * @version: 1.0
 */
public class ClientConnectServerThread extends Thread {
    //改线程需要持有socket
    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //用于在后台和服务器进行通讯
        while (true) {
            System.out.println("客户端线程，等待从服务端发送的消息");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //如果服务器不发送Message对象，线程会阻塞
                Message message = (Message) ois.readObject();
                //判断message类型，做出相应的业务处理
                //如果读取到的是服务端返回的在线用户列表
                if (message.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)) {
                    //取出在线列表信息，并显示
                    String[] onLineUsers = message.getContent().split(" ");
                    System.out.println("=============当前在线用户列表如下：============");
                    for (int i = 0; i < onLineUsers.length; i++) {
                        System.out.println("用户:" + onLineUsers[i]);
                    }
                } else {
                    System.out.println("其他类型的业务逻辑带完善");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
