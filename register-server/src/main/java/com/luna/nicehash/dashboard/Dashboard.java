package com.luna.nicehash.dashboard;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.luna.nicehash.MyChromeDriver;
import com.luna.nicehash.entity.ApiKeyDO;
import com.luna.nicehash.entity.UserDO;
import com.luna.nicehash.login.Login;
import com.luna.nicehash.util.FileUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @Package: com.luna.com.luna.nicehash.dashboard
 * @ClassName: Dashboard
 * @Author: luna
 * @CreateTime: 2021/1/8 14:33
 * @Description:
 */
public class Dashboard {

    private static final String USER_ACCOUNT = "user_api.json";

    private static final String USER_ACCOUNT_TMP = "apiKey_tmp/apiKey_";

    private static final Logger log = LoggerFactory.getLogger(Dashboard.class);

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
        Thread.sleep(60000L);


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
     * 自动获取apiKey
     *
     * @param userList
     * @throws InterruptedException
     * @throws IOException
     * @throws UnsupportedFlavorException
     */
    public static void autoCrete(List<UserDO> userList)
            throws InterruptedException {
        List<ApiKeyDO> userApiKey = new ArrayList<ApiKeyDO>();
        for (UserDO userDO : userList) {
            Login.login(userDO.getEmail(), userDO.getPassword());
            Thread.sleep(2000L);
            dashboard();
            Thread.sleep(2000L);
            ApiKeyDO key = getKey(userDO.getPassword());
            FileUtil.writeSetting(USER_ACCOUNT_TMP + userDO.getEmail() + ".json", JSON.toJSONString(key),true);
            userApiKey.add(key);
            log.info("user={}, apiKey={}", userDO.getEmail(), key);
            MyChromeDriver.exit();
        }
        FileUtil.writeSetting(USER_ACCOUNT, JSON.toJSONString(userApiKey));
    }

    public static void main(String[] args) throws InterruptedException {

    }
}
