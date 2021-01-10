package com.luna.nicehash.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
                StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
