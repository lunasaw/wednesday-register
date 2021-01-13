package com.luna.nicehash.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author luna@mac
 * @className CountDown.java
 * @description TODO
 * @createTime 2021年01月12日 23:50:00
 */
public class CountDown {

    private static final Logger log = LoggerFactory.getLogger(CountDown.class);

    public static void countDown(long midTime) {

        while (midTime > 0) {
            midTime--;
            long ss = midTime % 60;
            log.info("距离下次操作还剩" + ss + "秒");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
