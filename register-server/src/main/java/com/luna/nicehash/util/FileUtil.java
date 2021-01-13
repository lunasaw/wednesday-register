package com.luna.nicehash.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Package: com.luna.nicehash.util
 * @ClassName: FileUtil
 * @Author: luna
 * @CreateTime: 2021/1/10 20:05
 * @Description:
 */
public class FileUtil {

    public static void writeSetting(String file, String json) {
        try {
            org.apache.commons.io.FileUtils.writeStringToFile(new File(file), json,
                StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeSetting(String file, String json,boolean Over) {
        try {
            org.apache.commons.io.FileUtils.writeStringToFile(new File(file), json,
                    StandardCharsets.UTF_8, Over);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
