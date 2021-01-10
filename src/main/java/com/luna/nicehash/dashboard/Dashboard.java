package com.luna.nicehash.dashboard;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.luna.nicehash.MyChromeDriver;
import com.luna.nicehash.entity.ApiKeyDO;
import com.luna.nicehash.entity.UserDO;
import com.luna.nicehash.login.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.luna.nicehash.dashboard
 * @ClassName: Dashboard
 * @Author: luna
 * @CreateTime: 2021/1/8 14:33
 * @Description:
 */
public class Dashboard {

    private static final String USER_ACCOUNT = "user_api.json";

    private static final Logger log          = LoggerFactory.getLogger(Dashboard.class);

    /**
     * 控制台
     * 
     * @throws InterruptedException
     */
    public static void dashboard() {
        // 手动跳转 防止不弹出
        MyChromeDriver.chromeDriver.get("https://www.nicehash.com/my/dashboard");

        try {
            // 服务选择
            MyChromeDriver.chromeDriver
                .findElement(By.cssSelector("#app>div.modal-bg.show>span>div>div>div>button"))
                .click();

            // 选择矿场
            MyChromeDriver.chromeDriver
                .findElement(
                    By.cssSelector("#app>div.modal-bg.show>span>div>div>div>div:nth-child(5)>div.col3>label>span"))
                .click();

            // 开始使用
            MyChromeDriver.chromeDriver
                .findElement(By.cssSelector("#app>div.modal-bg.show>span>div>div>div>button"))
                .click();
        } catch (Exception e) {

        }

    }

    /**
     * 获取apiKey
     * 
     * @param password
     * @return
     * @throws InterruptedException
     */
    public static ApiKeyDO getKey(String password)
        throws InterruptedException {
        MyChromeDriver.chromeDriver.get("https://www.nicehash.com/my/settings/keys");
        Thread.sleep(1000L);
        // 组织id
        WebElement organizationIdLabel = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#content>div.container.flex.mb32.settings-panel>div.content.ml32.ml0-sm-down.mt32-sm-down>div>div>div.row>div.col-sm-7.col-sm-12>small.text-muted.mb32>strong"));

        String organizationIdText = organizationIdLabel.getText();

        MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#content>div.container.flex.mb32.settings-panel>div.content.ml32.ml0-sm-down.mt32-sm-down>div>div>div.row>div.col-sm-5.col-xs-12.mt16-xs-down.mt32>button"))
            .click();
        // 密钥名称
        WebElement inputApiKeyName = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#app>div.modal-bg.show>span>div>div>div.modal-content>div.field-wrap.input-null>div.input-group>input"));
        // 清除输入框内容
        inputApiKeyName.clear();
        // 输入密钥名称
        inputApiKeyName.sendKeys("w");
        // 休息
        Thread.sleep(3000L);
        // 创建密钥
        MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#app>div.modal-bg.show>span>div>div>div.modal-content>div.row.mt40>div>button.btn.primary.normal"))
            .click();

        // 输入密码
        WebElement inputPassword = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#app>div.modal-bg.show>span:nth-child(2)>div>div>div.modal-content>div>form>div.mb24.input-group.auth>input"));
        // 清除输入框内容
        inputPassword.clear();
        // 输入密码
        inputPassword.sendKeys(password);
        // 休息
        Thread.sleep(3000L);
        MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#app>div.modal-bg.show>span:nth-child(2)>div>div>div.modal-content>div>form>div.row>div>button"))
            .click();

        String apikey = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#app>div.modal-bg.show>span:nth-child(2)>div>div>div.modal-content>div>div:nth-child(2)>div.input-group>input"))
            .getAttribute("value");

        String apiSecret = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#app>div.modal-bg.show>span:nth-child(2)>div>div>div.modal-content>div>div:nth-child(3)>div.input-group>input"))
            .getAttribute("value");

        // 确认已保存
        MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#app>div.modal-bg.show>span:nth-child(2)>div>div>div.modal-content>div>div.mb32.checkbox>label>span"))
            .click();
        // 激活
        MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#app>div.modal-bg.show>span:nth-child(2)>div>div>div.modal-content>div>div.row>div>button"))
            .click();
        Thread.sleep(3000L);
        // 地址
        MyChromeDriver.chromeDriver.get("https://www.nicehash.com/my/mining/rigs");
        MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#content>div.container-full-white>div:nth-child(2)>div.row.header>div:nth-child(1)>button"))
            .click();

        MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#app>div.modal-bg.show>span>div>div>div.modal-content>div>div>div.mt32.field-wrap.input-undefined>div.input-group.medium>button"))
            .click();

        String address = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#app>div.modal-bg.show>span>div>div>div.modal-content>div>div>div.mt32.field-wrap.input-undefined>div.input-group.medium>input"))
            .getAttribute("value");
        Thread.sleep(1000L);

        MyChromeDriver.chromeDriver.get("https://www.nicehash.com/my/dashboard");

        return new ApiKeyDO(organizationIdText, address, apikey, apiSecret);
    }

    /**
     * 清除浏览器记录
     */
    public static void exit() {
        MyChromeDriver.chromeDriver.getLocalStorage().clear();
        MyChromeDriver.chromeDriver.manage().deleteAllCookies();
    }

    /**
     * 自动获取apiKey
     * 
     * @param userList
     * @throws InterruptedException
     * @throws IOException
     * @throws UnsupportedFlavorException
     */
    public static void autoCrete(List<UserDO> userList)
        throws InterruptedException {
        List<ApiKeyDO> userApiKey = new ArrayList<>();
        for (UserDO userDO : userList) {
            Login.login(userDO.getEmail(), userDO.getPassword());
            Thread.sleep(2000L);
            dashboard();
            Thread.sleep(2000L);
            ApiKeyDO key = getKey(userDO.getPassword());
            userApiKey.add(key);
            log.info("user={}, apiKey={}", userDO.getEmail(), key);
            exit();
        }
        writeUserApiKeySetting(JSON.toJSONString(userApiKey));
    }

    private static void writeUserApiKeySetting(String json) {
        try {
            org.apache.commons.io.FileUtils.writeStringToFile(new File(USER_ACCOUNT), json,
                StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<UserDO> userList = new ArrayList<>();
        userList.add(new UserDO("pascalqq+0280@protonmail.com", "i6ya@prG29"));
        userList.add(new UserDO("pascalqq+0281@protonmail.com", "M8*c@iKZQH"));
        autoCrete(userList);
    }
}
