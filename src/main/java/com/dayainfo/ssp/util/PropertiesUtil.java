package com.dayainfo.ssp.util;

/**
 * 配置文件类
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
    /**
     * 根据url读配置文件
     */
    private static Properties loadPropertiesByUrl(String url) {
        if (!StringUtil.isEmpty(url)) {
            InputStream out = null;
            Properties dbProps = new Properties();
            try {
                out = PropertiesUtil.class.getResource(url).openStream();
                dbProps.load(out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return dbProps;
        } else {
            return null;
        }
    }

    /**
     * 读配置文件
     */
    public static String readProperties(String path, String name) {
        if (!StringUtil.isEmpty(name) && !StringUtil.isEmpty(path)) {
            Properties dbProps = loadPropertiesByUrl(path);
            return dbProps.getProperty(name);
        }
        return "";
    }

    public static Map<Object, Object> readMulProperties(String path) {
        Map<Object, Object> map = new HashMap<Object, Object>(10);
        if (!StringUtil.isEmpty(path)) {
            Properties dbProps = loadPropertiesByUrl(path);
            for (Map.Entry<Object, Object> entry : dbProps.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(readProperties("/config/jdbc.properties", "jdbc.url"));
    }
}
