package com.wpt.qqcommon;

/**
 * @author wpt@onlying.cn
 * @date 2024/1/30 21:43
 */
public interface MessageType {
    //在接口中定义不同常亮，不同的值表示不同的消息类型
    String MESSAGE_LOGIN_SUCCEED = "1";//登录成功
    String MESSAGE_LOGIN_FAIL = "2";//登录失败
    String MESSAGE_COMN_MES = "3";//普通信息包
    String MESSAGE_GET_ONLINE_FRIEND = "4";//要求返回在线用户列表
    String MESSAGE_RET_ONLINE_FRIEND = "5";//在线用户列表
    String MESSAGE_CLIENT_EXIT = "6";//客户端请求退出

}
