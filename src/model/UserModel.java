package model;

import java.io.Serializable;

public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;
    private String loginCd;
    private String loginPw;

    public UserModel(int userId, String loginCd, String loginPw) {
        this.userId = userId;
        this.loginCd = loginCd;
        this.loginPw = loginPw;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLoginCd() {
        return loginCd;
    }

    public void setLoginCd(String loginCd) {
        this.loginCd = loginCd;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }
}
