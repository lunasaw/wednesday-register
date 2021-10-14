package com.luna.nicehash.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.luna.nicehash.MyChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Package: com.luna.com.luna.nicehash.login
 * @ClassName: Login
 * @Author: luna
 * @CreateTime: 2021/1/8 14:23
 * @Description:
 */
public class Login {

    private static final String DASHBOARD_ID_SELECTOR = "#content>div.main-top-menu>ul>li:nth-child(1)>a";

    private static final String LOGIN_SELECTOR        = "#content>div>div.box>div>div>form>div:nth-child(1)>div>input";

    /**
     * 登录
     * 
     * @param email
     * @param password
     * @throws InterruptedException
     */
    public static void login(String email, String password) throws InterruptedException {
        MyChromeDriver.chromeDriver.get("https://www.nicehash.com/my/dashboard");
        WebDriverWait wait = new WebDriverWait(MyChromeDriver.chromeDriver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(LOGIN_SELECTOR)));
        Thread.sleep(2000L);
        // 使用额email定位手机号的输入框
        WebElement inputEmail = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(LOGIN_SELECTOR));
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

        // 同意协议
        MyChromeDriver.chromeDriver
            .findElement(By.cssSelector("#content>div>div.box>div>div>form>div.mb24.checkbox>label>span"))
            .click();

        // 提交
        MyChromeDriver.chromeDriver
            .findElement(By.cssSelector("#content>div>div.box>div>div>form>div.text-center.mb40>div>button"))
            .click();
        wait = new WebDriverWait(MyChromeDriver.chromeDriver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(DASHBOARD_ID_SELECTOR)));
    }
}
