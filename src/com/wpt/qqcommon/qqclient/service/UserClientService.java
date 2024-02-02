package com.wpt.qqcommon.qqclient.service;/**
 * @author wpt@onlying.cn
 * @date 2024/2/2 19:47
 */

import com.wpt.qqcommon.Message;
import com.wpt.qqcommon.MessageType;
import com.wpt.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @projectName: QQClient
 * @package: com.wpt.qqcommon.qqclient.service
 * @className: UserClientService
 * @author: wpt
 * @description: 完成用户登录验证和注册等功能
 * @date: 2024/2/2 19:47
 * @version: 1.0
 */
public class UserClientService {
    //其他类可能使用user信息，做成属性
    private User u = new User();

    private Socket socket;

    //根据userId和pwd验证用户是否有效
    public boolean checkUser(String userId, String pwd) throws IOException, ClassNotFoundException {
        boolean b = false;
        //创建User对象
        u.setUserID(userId);
        u.setPasswd(pwd);
        //链接到服务器端，发送u对象
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
        //得到ObjectOutputStream对象
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(u);//发送User对象

        //读取服务端回复的Message对象
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message ms = (Message) ois.readObject();
        if (ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) {
            //创建和服务器保持通信的线程--ClientConnectServerThread
            ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
            //启动客户端线程
            clientConnectServerThread.start();
            //为了客户端的扩展，将线程放入集合，统一进行管理
            ManageClientConnectServerThread.addClientConnectSgeterverThread(userId, clientConnectServerThread);
            b = true;
        } else {
            //登录失败，不启动通信线程，关闭socket
            socket.close();

        }
        return b;
    }

}
