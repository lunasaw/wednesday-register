package com.luna.nicehash.dto;

import com.luna.nicehash.entity.ApiKeyDO;
import com.luna.nicehash.entity.UserDO;

/**
 * @Package: com.luna.nicehash.dto
 * @ClassName: UserDTO
 * @Author: luna
 * @CreateTime: 2021/1/10 19:33
 * @Description:
 */
public class UserDTO {

    private String email;

    private String password;

    /** 组织ID */
    private String organizationId;

    /** 地址 */
    private String address;

    /** apiKey */
    private String apiKey;

    /** apiSecret */
    private String apiSecret;

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public UserDTO setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public UserDTO setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public UserDTO setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
        return this;
    }

    public UserDTO() {}

    public UserDTO(String email, String password, String organizationId, String address, String apiKey,
        String apiSecret) {
        this.email = email;
        this.password = password;
        this.organizationId = organizationId;
        this.address = address;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }
}
