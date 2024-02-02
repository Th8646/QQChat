package com.wpt.qqcommon;/**
 * @author wpt@onlying.cn
 * @date 2024/1/30 21:35
 */

import java.io.Serializable;

/**
 * @projectName: QQServer
 * @package: com.wpt.qqcommon
 * @className: User
 * @author: wpt
 * @description: TODO
 * @date: 2024/1/30 21:35
 * @version: 1.0
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userID;
    private String passwd;
    public User(){

    }
    public User(String userID, String passwd) {
        this.userID = userID;
        this.passwd = passwd;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
