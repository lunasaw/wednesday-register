package com.luna.nicehash.dashboard;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.luna.nicehash.MyChromeDriver;
import com.luna.nicehash.entity.ApiKeyDO;
import com.luna.nicehash.entity.UserDO;
import com.luna.nicehash.login.Login;
import com.luna.nicehash.util.CountDown;
import com.luna.nicehash.util.FileUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    private static final String USER_ACCOUNT                 = "user_api.json";

    private static final String USER_ACCOUNT_TMP             = "apiKey_tmp/apiKey_";

    private static final Logger log                          = LoggerFactory.getLogger(Dashboard.class);

    private static final String ORGANIZATION_ID_SELECTOR     =
        "#content>div.container.flex.mb32.settings-panel>div.content.ml32.ml0-sm-down.mt32-sm-down>div>div>div.row>div.col-sm-7.col-sm-12>small.text-muted.mb32>strong";

    private static final String CREATE_API_KEY_SELECTOR      =
        "#content>div.container.flex.mb32.settings-panel>div.content.ml32.ml0-sm-down.mt32-sm-down>div>div>div.row>div.col-sm-5.col-xs-12.mt16-xs-down.mt32>button";

    private static final String INPUT_API_KEY_NAME_SELECTOR  =
        "#app>div.modal-bg.show>span>div>div>div.modal-content>div.field-wrap.input-null>div.input-group>input";

    private static final String ENSURE_API_KEY_NAME_SELECTOR =
        "#app>div.modal-bg.show>span>div>div>div.modal-content>div.row.mt40>div>button.btn.primary.normal";

    private static final String INPUT_PASSWORD_SELECTOR      =
        "#app>div.modal-bg.show>span:nth-child(2)>div>div>div.modal-content>div>form>div.mb24.input-group.auth>input";

    private static final String SUBMIT_PASSWORD_SELECTOR     =
        "#app>div.modal-bg.show>span:nth-child(2)>div>div>div.modal-content>div>form>div.row>div>button";

    private static final String ENSURE_SAVE_KET_SELECTOR     =
        "#app>div.modal-bg.show>span:nth-child(2)>div>div>div.modal-content>div>div.mb32.checkbox>label>span";

    private static final String ENSURE_ACTIVE_SELECTOR       =
        "#app>div.modal-bg.show>span:nth-child(2)>div>div>div.modal-content>div>div.row>div>button";

    private static final String API_KEY_SELECTOR             =
        "#app>div.modal-bg.show>span:nth-child(2)>div>div>div.modal-content>div>div:nth-child(2)>div.input-group>input";
    private static final String API_SECRET_SELECTOR          =
        "#app>div.modal-bg.show>span:nth-child(2)>div>div>div.modal-content>div>div:nth-child(3)>div.input-group>input";

    /** api 创建完成检测选择器 */
    private static final String API_CREATED_SELECTOR         =
        "#content>div.container.flex.mb32.settings-panel>div.content.ml32.ml0-sm-down.mt32-sm-down>div>div>div.relative>div>div>table>tbody>tr";

    private static final String MINNER_ADDRESS_SELECTOR      =
        "#content>div.container-full-whitex>div:nth-child(1)>div.row.header>div:nth-child(1)>button";
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

            MyChromeDriver.chromeDriver
                    .findElement(By.cssSelector(
                    "#app>div.modal-bg.show>span>div>div>div>div:nth-child(5)>div.col2>div.mt16>div>label"))
                    .click();

            // 开始使用
            MyChromeDriver.chromeDriver
                .findElement(By.xpath("/html/body/div/div[3]/span/div/div/div/button"))
                .click();

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        WebDriverWait wait = new WebDriverWait(MyChromeDriver.chromeDriver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ORGANIZATION_ID_SELECTOR)));
        Thread.sleep(2000L);
        // 组织id
        WebElement organizationIdLabel =
            MyChromeDriver.chromeDriver.findElement(By.cssSelector(ORGANIZATION_ID_SELECTOR));
        String organizationIdText = organizationIdLabel.getText();
        Thread.sleep(500);

        // 创建API按钮
        MyChromeDriver.chromeDriver.findElement(By.cssSelector(CREATE_API_KEY_SELECTOR)).click();

        Thread.sleep(500);
        // 密钥名称
        WebElement inputApiKeyName =
            MyChromeDriver.chromeDriver.findElement(By.cssSelector(INPUT_API_KEY_NAME_SELECTOR));
        // 清除输入框内容
        inputApiKeyName.clear();
        // 输入密钥名称
        inputApiKeyName.sendKeys("w");
        // 休息
        Thread.sleep(1000);
        // 创建密钥
        MyChromeDriver.chromeDriver.findElement(By.cssSelector(ENSURE_API_KEY_NAME_SELECTOR)).click();
        Thread.sleep(1000);
        // 输入密码
        WebElement inputPassword = MyChromeDriver.chromeDriver.findElement(By.cssSelector(INPUT_PASSWORD_SELECTOR));
        // 清除输入框内容
        inputPassword.clear();
        // 输入密码
        inputPassword.sendKeys(password);
        // 休息
        Thread.sleep(1000);
        MyChromeDriver.chromeDriver.findElement(By.cssSelector(SUBMIT_PASSWORD_SELECTOR)).click();

        String apikey = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(API_KEY_SELECTOR)).getAttribute("value");
        Thread.sleep(500);
        String apiSecret = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(API_SECRET_SELECTOR)).getAttribute("value");

        // 确认已保存
        MyChromeDriver.chromeDriver.findElement(By.cssSelector(ENSURE_SAVE_KET_SELECTOR))
            .click();
        Thread.sleep(500);
        // 激活
        MyChromeDriver.chromeDriver.findElement(By.cssSelector(ENSURE_ACTIVE_SELECTOR)).click();

        log.info("发送api激活邮件 等待40秒");
        // CountDown.countDown(40L);

        // 等待api标签出现
        try {
            wait = new WebDriverWait(MyChromeDriver.chromeDriver, 40);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(API_CREATED_SELECTOR)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            wait = new WebDriverWait(MyChromeDriver.chromeDriver, 40);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(API_CREATED_SELECTOR)));
        }
        // 地址
        MyChromeDriver.chromeDriver.get("https://www.nicehash.com/my/mining/rigs");
        try {
            wait = new WebDriverWait(MyChromeDriver.chromeDriver, 40);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(MINNER_ADDRESS_SELECTOR)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            wait = new WebDriverWait(MyChromeDriver.chromeDriver, 40);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(MINNER_ADDRESS_SELECTOR)));
        }
        MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(MINNER_ADDRESS_SELECTOR))
            .click();

        try {
            MyChromeDriver.chromeDriver
                .findElement(
                    By.cssSelector("#app>div.vue-portal-target>span>div.tour-step>div.buttons>a.btn.small.nobtn.mr16"))
                .click();
        } catch (Exception e) {

        }

        Thread.sleep(500);
        String address = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#app>div.modal-bg.show>span>div>div>div.modal-content>div>div>div.mt32.field-wrap.input-undefined>div.input-group.medium>input"))
            .getAttribute("value");
        Thread.sleep(500);

        MyChromeDriver.chromeDriver.get("https://www.nicehash.com/my/dashboard");
        Thread.sleep(500);
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
            log.info("user {} start to create apiKey", JSON.toJSONString(userDO));
            Login.login(userDO.getEmail(), userDO.getPassword());
            dashboard();
            ApiKeyDO key = getKey(userDO.getPassword());
            FileUtil.writeSetting(USER_ACCOUNT_TMP + userDO.getEmail() + ".json", JSON.toJSONString(key), true);
            userApiKey.add(key);
            log.info("user={}, apiKey={}", userDO.getEmail(), JSON.toJSONString(key));
            MyChromeDriver.exit();
        }
        FileUtil.writeSetting(USER_ACCOUNT, JSON.toJSONString(userApiKey));
    }
}
