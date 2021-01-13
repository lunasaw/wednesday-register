package com.luna.nicehash.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author luna@mac
 * @className ParseJsonFile.java
 * @description TODO
 * @createTime 2021年01月12日 23:28:00
 */
public class ParseJsonFile<T> {

    public List<?> readFile(Class c, String jsonFile){
        String text = null;
        try {
            InputStream inputStream = new FileInputStream(jsonFile);
            text = IOUtils.toString(inputStream, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseArray(text, c);
    }
}
