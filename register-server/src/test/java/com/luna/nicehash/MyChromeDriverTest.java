package com.luna.nicehash;

import com.luna.nicehash.login.Login;
import com.luna.nicehash.register.Register;
import org.junit.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @Package: com.luna.nicehash
 * @ClassName: MyChromeDriverTest
 * @Author: luna
 * @CreateTime: 2021/1/10 16:50
 * @Description:
 */
public class MyChromeDriverTest {

    @Test
    public void register() throws InterruptedException {
        Register.autoRegister(280, 10, 2);
    }

    @Test
    public void getKey() throws InterruptedException, IOException, UnsupportedFlavorException {
        Login.login("pascalqq+0280@protonmail.com", "i6ya@prG29");
        // Thread.sleep(5000L);
        // Dashboard.getKey("q7,$M28FHk");
        // log.info("");
    }

    @Test
    public void atest() {
        System.out.println(System.getProperty("user.dir") + "\\");
        URL resource = this.getClass().getClassLoader().getResource("chromedriver.exe");
        System.out.println(resource.getPath());

    }
}
