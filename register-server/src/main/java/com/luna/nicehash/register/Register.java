package com.luna.nicehash.register;

import java.util.ArrayList;
import java.util.List;

import com.luna.nicehash.entity.UserDO;
import com.luna.nicehash.util.CreateKeyUtil;
import com.luna.nicehash.util.FileUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.luna.nicehash.MyChromeDriver;

/**
 * @Package: com.luna
 * @ClassName: Register
 * @Author: luna
 * @CreateTime: 2021/1/7 16:05
 * @Description: chrome 87.0.4280.88 版本 驱动地址 https://npm.taobao.org/mirrors/chromedriver/87.0.4280.88/
 */
public class Register {
    private static final String USER_ACCOUNT         = "user_account.json";

    private static final String USER_ACCOUNT_TMP     = "account_tmp/account_";

    private static final Logger log                  = LoggerFactory.getLogger(Register.class);

    private static final String REGISTER_ID_SELECTOR =
        "#content>div>div.box>div>div:nth-child(2)>div>form>div.text-center.mb40>button";

    /**
     *
     * @param email
     * @param password
     * 1个小写字母
     * 1个大写字母
     * 最少8个字符
     * 1个数字
     * 1个特殊字符
     * 密码正确匹配
     * @throws InterruptedException
     */
    public static void register(String email, String password) throws InterruptedException {
        MyChromeDriver.chromeDriver.get("https://www.nicehash.com/my/register");
        Thread.sleep(2000L);
        // 使用额email定位邮箱的输入框
        WebElement inputEmail = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector("#content>div>div.box>div>div>form>div:nth-child(1)>div>input"));
        // 清除输入框内容
        inputEmail.clear();
        // 输入邮箱
        inputEmail.sendKeys(email);
        // 休息一秒
        Thread.sleep(1000L);

        WebElement inputPassword = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#content>div>div.box>div>div>form>div:nth-child(2)>div>input"));
        // 清除输入框内容
        inputPassword.clear();
        // 输入密码
        inputPassword.sendKeys(password);
        // 休息一秒
        Thread.sleep(1000L);

        WebElement ensurePassword = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "#content>div>div.box>div>div>form>div:nth-child(3)>div>input"));
        // 清除输入框内容
        ensurePassword.clear();
        // 输入确认密码
        ensurePassword.sendKeys(password);
        // 休息一秒
        Thread.sleep(1000L);

        // 同意协议
        MyChromeDriver.chromeDriver
            .findElement(By.cssSelector("#content>div>div.box>div>div>form>div.mb16>div>label>span"))
            .click();

        // 提交
        MyChromeDriver.chromeDriver
            .findElement(By.cssSelector("#content>div>div.box>div>div>form>div:nth-child(8)>div>button"))
            .click();
    }

    /**
     * 自动注册
     * 
     * @param startId 开始id
     * @param passwordLength 密码长度
     * @throws InterruptedException
     */
    public static void autoRegister(Integer startId, int passwordLength, int size) throws InterruptedException {
        List<UserDO> userList = new ArrayList<UserDO>();
        for (int i = 0; i < size; i++) {
            String id = String.valueOf(startId + i);
            String email = "pascalqq+" + (id.length() > 4 ? id : "0" + id) + "@protonmail.com";
            String password = CreateKeyUtil.getRandomKeys(passwordLength);
            log.info("email={}, password={}", email, password);
            UserDO userDO = new UserDO(email, password);
            userList.add(userDO);
            register(email, password);
            FileUtil.writeSetting(USER_ACCOUNT_TMP + email + ".json", JSON.toJSONString(userDO) + "\n");
            WebDriverWait wait = new WebDriverWait(MyChromeDriver.chromeDriver, 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(REGISTER_ID_SELECTOR)));
        }
        FileUtil.writeSetting(USER_ACCOUNT, JSON.toJSONString(userList) + "\n");
    }
}
