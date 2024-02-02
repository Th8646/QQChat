package com.wpt.qqcommon.qqclient.service;/**
 * @author wpt@onlying.cn
 * @date 2024/2/2 20:05
 */

import com.wpt.qqcommon.Message;

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
        while (true){
            System.out.println("客户端线程，等待从服务端发送的消息");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //如果服务器不发送Message对象，线程会阻塞
                Message ms = (Message) ois.readObject();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
