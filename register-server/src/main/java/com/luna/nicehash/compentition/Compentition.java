package com.luna.nicehash.compentition;

import com.alibaba.fastjson.JSON;
import com.luna.common.utils.CountDownUtils;
import com.luna.nicehash.MyChromeDriver;
import com.luna.nicehash.util.FileUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luna
 * 2021/5/27
 */
public class Compentition {

    public static final String LOGIN_SELECTOR = "body>table:nth-child(1)>tbody>tr>td>img";

    public static Questtion getAnswer(int i) {
        MyChromeDriver.chromeDriver.get("http://yctu.91job.org.cn/contest/question?page=" + i);

        String number = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "body>div.all>div.title>b>font"))
            .getText();
        String title = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "body>div>div.title>b"))
            .getText();

        String selecterA = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "body>div.all>div.answer>ul>li:nth-child(1)>label"))
            .getText();

        String selecterB = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                "body>div.all>div.answer>ul>li:nth-child(2)>label"))
            .getText();

        String selecterC = null;
        String selecterD = null;
        if (!title.contains("判断")) {
            selecterC = MyChromeDriver.chromeDriver
                .findElement(By.cssSelector(
                    "body>div.all>div.answer>ul>li:nth-child(3)>label"))
                .getText();

            selecterD = MyChromeDriver.chromeDriver
                .findElement(By.cssSelector(
                    "body>div.all>div.answer>ul>li:nth-child(4)>label"))
                .getText();
        }
        String answer = MyChromeDriver.chromeDriver
            .findElement(By.cssSelector(
                " body>div.all>div.right>p>font>b"))
            .getText();

        return new Questtion(number, title, selecterA, selecterB, selecterC, selecterD, answer);
    }

    public static void login() {
        MyChromeDriver.chromeDriver.get("http://yctu.91job.org.cn/user/login?referer=/contest/question");
        WebDriverWait wait = new WebDriverWait(MyChromeDriver.chromeDriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(LOGIN_SELECTOR)));
    }

    public static void main(String[] args) {
        login();
        List<Questtion> list = new ArrayList<>();
        for (int i = 0; i < 539; i++) {
            Questtion answer = getAnswer(i);
            list.add(answer);
            System.out.println(answer);
            CountDownUtils.countDown(1);
            FileUtil.writeSetting("question/base/" + answer.getNumber() + ".json", JSON.toJSONString(list), true);
        }
        FileUtil.writeSetting("question/" + "questiono.json", JSON.toJSONString(list), true);
    }
}

class Questtion {
    /**
     * 题号
     */
    private String number;
    /**
     * 标题
     */
    private String title;
    /**
     * 选项A
     */
    private String selecterA;
    /**
     * 选项B
     */
    private String selecterB;
    /**
     * 选项C
     */
    private String selecterC;
    /**
     * 选项D
     */
    private String selecterD;
    /**
     * 答案
     */
    private String answer;

    public Questtion(String number, String title, String selecterA, String selecterB, String selecterC,
        String selecterD, String answer) {
        this.number = number;
        this.title = title;
        this.selecterA = selecterA;
        this.selecterB = selecterB;
        this.selecterC = selecterC;
        this.selecterD = selecterD;
        this.answer = answer;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSelecterA() {
        return selecterA;
    }

    public void setSelecterA(String selecterA) {
        this.selecterA = selecterA;
    }

    public String getSelecterB() {
        return selecterB;
    }

    public void setSelecterB(String selecterB) {
        this.selecterB = selecterB;
    }

    public String getSelecterC() {
        return selecterC;
    }

    public void setSelecterC(String selecterC) {
        this.selecterC = selecterC;
    }

    public String getSelecterD() {
        return selecterD;
    }

    public void setSelecterD(String selecterD) {
        this.selecterD = selecterD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Questtion{" +
            "number='" + number + '\'' +
            ", title='" + title + '\'' +
            ", selecterA='" + selecterA + '\'' +
            ", selecterB='" + selecterB + '\'' +
            ", selecterC='" + selecterC + '\'' +
            ", selecterD='" + selecterD + '\'' +
            ", answer='" + answer + '\'' +
            '}';
    }
}