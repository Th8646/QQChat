package com.wpt.qqclient.service;/**
 * @author wpt@onlying.cn
 * @date 2024/2/21 21:50
 */

import com.wpt.qqcommon.Message;
import com.wpt.qqcommon.MessageType;

import java.io.*;

/**
 * @projectName: QQClient
 * @package: com.wpt.qqclient.service
 * @className: FileClientService
 * @author: wpt
 * @description: 完成文件的传输
 * @date: 2024/2/21 21:50
 * @version: 1.0
 */
public class FileClientService {
    /**
     * @param src      原文件路径
     * @param dest     把文件传输到对方的哪个目录
     * @param senderId 发送用户Id
     * @param getterId 接收用户Id
     */
    public void sendFileToOne(String src, String dest, String senderId, String getterId) {

        // 读取src文件--->message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_FILE_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setSrc(src);
        message.setDset(dest);
        System.out.println(senderId + "想要");

        //  需求将文件读取
        FileInputStream fileInputStream = null;
        byte[] fileBytes = new byte[(int) new File(src).length()];

        try {
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes);// 将src文件读入程序的字节数组
            //将文件对应的字节数组设置message
            message.setFileBytes(fileBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //关闭流
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //提示信息
        System.out.println(senderId + "给" + getterId + "发送文件：" + src + "到对方的" + dest);

        //发送
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerThread(senderId)
                            .getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
