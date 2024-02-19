package com.wpt.qqclient.service;/**
 * @author wpt@onlying.cn
 * @date 2024/2/2 20:21
 */

import java.util.HashMap;

/**
 * @projectName: QQClient
 * @package: com.wpt.qqcommon.qqclient.service
 * @className: ManageClientConnectServerThread
 * @author: wpt
 * @description: 管理客户端链接到服务器线程的类
 * @date: 2024/2/2 20:21
 * @version: 1.0
 */
public class ManageClientConnectServerThread {
    //把线程放入HashMap集合，key为用户id，value是线程对象
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    //将线程加入集合
    public static void addClientConnectSgeterverThread(String userId,
                                                    ClientConnectServerThread clientConnectServerThread){
        hm.put(userId,clientConnectServerThread);
    }
    //线程从集合取出  通过userID得到线程
    public static  void getClientConnectSgeterverThread(String userId){
        hm.get(userId);
    }

}
