package com.luna.nicehash;

import com.alibaba.fastjson.JSON;
import com.luna.nicehash.dashboard.Dashboard;
import com.luna.nicehash.entity.ApiKeyDO;
import com.luna.nicehash.entity.UserDO;
import com.luna.nicehash.login.Login;
import com.luna.nicehash.register.Register;
import org.junit.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
        Register.autoRegister(300, 10, 1);
    }

    @Test
    public void getKey() throws InterruptedException, IOException, UnsupportedFlavorException {
        Login.login("pascalqq+0282@protonmail.com", "aNvrB1@0HR!x");
        Thread.sleep(5000L);
        ApiKeyDO key = Dashboard.getKey("aNvrB1@0HR!x");
        System.out.println(JSON.toJSONString(key));
    }

    @Test
    public void atest() {
        System.out.printf(System.getProperty("os.name"));

//        System.out.println(System.getProperty("user.dir") + "\\");
//        URL resource = this.getClass().getClassLoader().getResource("chromedriver.exe");
//        System.out.println(resource.getPath());
    }

    @Test
    public void autoGetKey() throws InterruptedException {
        List<UserDO> userList = new ArrayList<UserDO>();
        userList.add(new UserDO("pascalqq+0301@protonmail.com", "3gc.gWyM,j"));
        Dashboard.autoCrete(userList);
    }
}
