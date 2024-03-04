package com.wpt.qqclient.service;/**
 * @author wpt@onlying.cn
 * @date 2024/2/2 20:05
 */

import com.wpt.qqcommon.Message;
import com.wpt.qqcommon.MessageType;

import java.io.*;
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
                if (message.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)) {//显示在线用户列表
                    //取出在线列表信息，并显示
                    String[] onLineUsers = message.getContent().split(" ");
                    System.out.println("=============当前在线用户列表如下：============");
                    for (int i = 0; i < onLineUsers.length; i++) {
                        System.out.println("用户:" + onLineUsers[i]);
                    }
                } else if (message.getMesType().equals(MessageType.MESSAGE_COMN_MES)) {//私聊消息
                    //把服务器端转发的消息显示到控制台
                    System.out.println("\n" + message.getSender() +
                            "对" + message.getGetter() + "说" + message.getContent());
                } else if (message.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)) {//群发消息
                    System.out.println("\n" + message.getSender() + "对大家说：" + message.getContent());

                } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {//文件消息
                    System.out.println("\n" + message.getSender() + " 给 " + message.getGetter() + "发文件："
                            + message.getSrc() + " 到： " +message.getDset());
                    //取出message对象的字节数组，通过文件输出流写入到磁盘
                    FileOutputStream fileOutputStream = new FileOutputStream(message.getDset());
                    fileOutputStream.write(message.getFileBytes());
                    fileOutputStream.close();
                    System.out.println("\n保存文件成功~");
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
