package com.luna.nicehash;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.luna.nicehash.login.Login;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Package: com.luna.com.luna.nicehash
 * @ClassName: ChromeDriver
 * @Author: luna
 * @CreateTime: 2021/1/8 14:23
 * @Description:
 */
public class MyChromeDriver {

    /** 创建浏览器对象 */
    public static org.openqa.selenium.chrome.ChromeDriver chromeDriver;

    private static final Logger                           log = LoggerFactory.getLogger(MyChromeDriver.class);

    static {
        MyChromeDriver myChromeDriver = new MyChromeDriver();
        myChromeDriver.start();
    }

    public void start() {
        // 配置本地浏览器驱动路径
        System.getProperties().setProperty("webdriver.chrome.driver",
            Objects.requireNonNull(this.getClass().getClassLoader().getResource("chromedriver.exe")).getPath());
        chromeDriver = new org.openqa.selenium.chrome.ChromeDriver();
        // 设置浏览器窗口最大化
        chromeDriver.manage().window().maximize();
        // 设置隐式等待时间，根据目标网页的响应速度设置超时时间
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * 清除浏览器记录
     */
    public static void exit() {
        MyChromeDriver.chromeDriver.getLocalStorage().clear();
        MyChromeDriver.chromeDriver.manage().deleteAllCookies();
        MyChromeDriver.chromeDriver.getSessionStorage().clear();
    }

    public static void main(String[] args) throws InterruptedException {
        Login.login("pascalqq+0280@protonmail.com", "i6ya@prG29");
    }
}
