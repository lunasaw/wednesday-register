package com.luna.nicehash.entity;

/**
 * @Package: com.luna.nicehash.entity
 * @ClassName: UserDO
 * @Author: luna
 * @CreateTime: 2021/1/10 16:35
 * @Description:
 */
public class UserDO {

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public UserDO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDO setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDO() {}
}
