package com.luna.nicehash;

import com.alibaba.fastjson.JSON;
import com.luna.nicehash.dashboard.Dashboard;
import com.luna.nicehash.entity.ApiKeyDO;
import com.luna.nicehash.entity.UserDO;
import com.luna.nicehash.login.Login;
import com.luna.nicehash.register.Register;
import com.luna.nicehash.util.CountDown;
import com.luna.nicehash.util.FileUtil;
import com.luna.nicehash.util.ParseJsonFile;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
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
    public void getKey() throws InterruptedException, IOException, UnsupportedFlavorException {
        Login.login("pascalqq+0282@protonmail.com", "aNvrB1@0HR!x");
        Thread.sleep(5000L);
        ApiKeyDO key = Dashboard.getKey("aNvrB1@0HR!x");
        System.out.println(JSON.toJSONString(key));
    }

    @Test
    public void atest() {
        System.out.printf(System.getProperty("os.name"));

        System.out.println(System.getProperty("user.dir") + "\\");
        URL resource = this.getClass().getClassLoader().getResource("chromedriver.exe");
        System.out.println(resource.getPath());
    }

    @Test
    public void autoGetKey() throws InterruptedException {
        List<UserDO> userList = new ArrayList<UserDO>();
        userList.add(new UserDO("luna_splendid+000000001@protonmail.com", "E8*T$.dJ4&"));
        Dashboard.autoCrete(userList);
    }

    @Test
    public void register() throws InterruptedException {
        Register.autoRegister(342, 10, 5);
        MyChromeDriver.chromeDriver.quit();
    }

    @Test
    public void registerOne() throws InterruptedException {
        Register.register("luna_splendid+000000001@protonmail.com", "E8*T$.dJ4&");
        MyChromeDriver.chromeDriver.quit();
    }

    @Test
    public void autoStart() throws InterruptedException {
        List<UserDO> list = (List<UserDO>)new ParseJsonFile<UserDO>().readListFile(UserDO.class, "user_account.json");
        System.out.println(JSON.toJSONString(list));
        Dashboard.autoCrete(list);
        MyChromeDriver.chromeDriver.quit();
    }

    @Test
    public void readJson() {
        int num = 284;
        int size = 8;
        ArrayList<UserDO> arrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String id = String.valueOf(num + i);
            String email = "pascalqq+" + (id.length() > 4 ? id : "0" + id) + "@protonmail.com";
            UserDO userDO = new ParseJsonFile<UserDO>().readFile(UserDO.class, "account_tmp/account_" + email + ".json");
            arrayList.add(userDO);
        }
        FileUtil.writeSetting("user_account.json", JSON.toJSONString(arrayList));
    }

    @Test
    public void autoRegisterAndGetApi() throws InterruptedException {
        Register.autoRegister(377, 10, 10);
        List<UserDO> list = (List<UserDO>)new ParseJsonFile<UserDO>().readListFile(UserDO.class, "user_account.json");
        System.out.printf(JSON.toJSONString(list));
        // 等待账号激活
        CountDown.countDown(40L);
        // Dashboard.autoCrete(list);
        MyChromeDriver.chromeDriver.quit();
    }
}
