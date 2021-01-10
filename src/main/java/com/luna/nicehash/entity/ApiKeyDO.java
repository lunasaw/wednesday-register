package com.luna.nicehash.entity;

/**
 * @Package: com.luna.nicehash.entity
 * @ClassName: ApiKeyDO
 * @Author: luna
 * @CreateTime: 2021/1/10 16:35
 * @Description:
 */
public class ApiKeyDO {

    /** 组织ID */
    private String organizationId;

    /** 地址 */
    private String address;

    /** apiKey */
    private String apiKey;

    /** apiSecret */
    private String apiSecret;

    public String getOrganizationId() {
        return organizationId;
    }

    public ApiKeyDO setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ApiKeyDO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public ApiKeyDO setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public ApiKeyDO setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
        return this;
    }

    public ApiKeyDO() {}

    public ApiKeyDO(String organizationId, String address, String apiKey, String apiSecret) {
        this.organizationId = organizationId;
        this.address = address;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }
}
