package com.luna;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.luna
 * @ClassName: Register
 * @Author: luna
 * @CreateTime: 2021/1/7 16:05
 * @Description: chrome 87.0.4280.88 版本 驱动地址 https://npm.taobao.org/mirrors/chromedriver/87.0.4280.88/
 */
public class Register {

    /** 创建浏览器对象 */
    public static ChromeDriver chromeDriver;

    static {
        // 配置本地浏览器驱动路径
        System.getProperties().setProperty("webdriver.chrome.driver",
            "src\\main\\resources\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        // 设置浏览器窗口最大化
        chromeDriver.manage().window().maximize();
        // 设置需要操作的网页url
        chromeDriver.get("https://www.nicehash.com/my/register");
        // 设置隐式等待时间，根据目标网页的响应速度设置超时时间
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

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
    public void register(String email, String password) throws InterruptedException {
        // 使用额email定位手机号的输入框
        WebElement inputEmail = chromeDriver
            .findElement(By.cssSelector("#content>div>div.box>div>div>form>div:nth-child(1)>div>input"));
        // 清除输入框内容
        inputEmail.clear();
        // 输入手机号
        inputEmail.sendKeys(email);
        // 休息一秒
        Thread.sleep(1000L);

        WebElement inputPassword = chromeDriver
            .findElement(By.cssSelector(
                "#content>div>div.box>div>div>form>div:nth-child(2)>div>input"));
        // 清除输入框内容
        inputPassword.clear();
        // 输入密码
        inputPassword.sendKeys(password);
        // 休息一秒
        Thread.sleep(1000L);

        WebElement ensurePassword = chromeDriver
            .findElement(By.cssSelector(
                "#content>div>div.box>div>div>form>div:nth-child(3)>div>input"));
        // 清除输入框内容
        ensurePassword.clear();
        // 输入确认密码
        ensurePassword.sendKeys(password);
        // 休息一秒
        Thread.sleep(1000L);


        // 同意协议
        chromeDriver.findElement(By.cssSelector("#content>div>div.box>div>div>form>div.mb16>div>label>span"))
                .click();

        // 提交
        chromeDriver.findElement(By.cssSelector("#content>div>div.box>div>div>form>div:nth-child(8)>div>button"))
            .click();
    }

    public static void main(String[] args) throws InterruptedException {
        Register register = new Register();
        register.register("15696756582@163.com", "123456");
    }

}
