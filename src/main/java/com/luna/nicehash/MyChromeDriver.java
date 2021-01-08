package com.luna.nicehash;

import com.luna.nicehash.dashboard.Dashboard;
import com.luna.nicehash.login.Login;
import com.luna.nicehash.register.Register;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.luna.nicehash
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
        // 配置本地浏览器驱动路径
        System.getProperties().setProperty("webdriver.chrome.driver",
            "src\\main\\resources\\chromedriver.exe");
        chromeDriver = new org.openqa.selenium.chrome.ChromeDriver();
        // 设置浏览器窗口最大化
        chromeDriver.manage().window().maximize();
        // 设置隐式等待时间，根据目标网页的响应速度设置超时时间
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void refresh() {
        chromeDriver.getSessionStorage().clear();
    }

    @Test
    public void register() throws InterruptedException {
        Register.autoRegister(280, 10, 2);
    }

    @Test
    public void getKey() throws InterruptedException, IOException, UnsupportedFlavorException {
        Login.login("pascalqq+0280@protonmail.com", "i6ya@prG29");
        Thread.sleep(5000L);
        Dashboard.getKey("q7,$M28FHk");
        log.info("");
    }
}
