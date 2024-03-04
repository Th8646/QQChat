package com.wpt.qqclient.view;/**
 * @author wpt@onlying.cn
 * @date 2024/2/1 21:07
 */

import com.wpt.qqclient.service.FileClientService;
import com.wpt.qqclient.service.MessageClientService;
import com.wpt.qqclient.service.UserClientService;
import com.wpt.qqclient.utils.Utility;

import java.io.IOException;

/**
 * @projectName: QQClient
 * @package: com.wpt.qqcommon.qqclient.view
 * @className: QQView
 * @author: wpt
 * @description: 显示主界面
 * @date: 2024/2/1 21:07
 * @version: 1.0
 */
public class QQView {
    private boolean loop = true;   //控制是否显示菜单
    private String key = ""; //接收用户的键盘输入
    private UserClientService userClientService = new UserClientService();//用于登录服务器/注册用户进行验证
    private MessageClientService messageClientService = new MessageClientService();//用于消息的发送/聊天/群聊
    private FileClientService fileClientService = new FileClientService();//用于传输文件

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        QQView qqView = new QQView();
        qqView.mianMenu();
        System.out.println("客户端退出系统...");
    }

    public void mianMenu() throws IOException, ClassNotFoundException {
        while (loop) {
            System.out.println("===========欢迎登录网络通信系统============");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");

            System.out.println("请输入你的选择：");
            key = Utility.readString(1);
            //根据用户的输入处理不同的逻辑
            switch (key) {
                case "1":
                    System.out.println("请输入用户号：");
                    String userID = Utility.readString(50);
                    System.out.println("请输入密码：");
                    String pwd = Utility.readString(50);

                    //到服务端，验证该用户是否合法...带完善（通过UserClientService类实现）
                    if (userClientService.checkUser(userID, pwd)) {
                        System.out.println("==============欢迎(用户" + userID + "登录成功)==============");
                        while (loop) {
                            System.out.println("\n=============网络通信系统二级菜单（用户" + userID + "）=================");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.println("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    //调用方法获取在线用户列表
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("请输入想对大家说的话");
                                    String s = Utility.readString(100);
                                    //将消息封装成message对象
                                    messageClientService.sendMessageToAll(s, userID);
                                    break;
                                case "3":
                                    System.out.println("请输入想聊天的用户号（在线）：");
                                    String getterId = Utility.readString(50);
                                    System.out.println("请输入想说的话：");
                                    String content = Utility.readString(100);
                                    //调用方法，将消息发送到服务端
                                    messageClientService.sendMessageToOne(content, userID, getterId);
                                    break;
                                case "4":
                                    System.out.println("请输入你想发送文件的用户(在线用户):");
                                    getterId = Utility.readString(50);
                                    System.out.println("请输入发送文件的路径(形式 d:\\xx.jpg)");
                                    String src = Utility.readString(100);
                                    System.out.println("请输入把文件发送到对方的路径(形式 d:\\xx.jpg)");
                                    String dest = Utility.readString(100);
                                    fileClientService.sendFileToOne(src, dest, userID, getterId);
                                    break;
                                case "9":
                                    //调用方法，给服务器端发送退出系统的message
                                    userClientService.logout();
                                    loop = false;
                                    break;
                            }
                        }

                    } else {
                        System.out.println("登录失败");
                    }
                    break;
                case "9":
                    loop = false;
                    break;
            }

        }
    }
}

