package com.js.gate.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
    private static final String path = "/constants.properties";
    private static final Resource resource = new ClassPathResource(path);
    private static Properties props=null;
    static {
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定key的value
     * @param key
     * @return
     */
    public static String getProperty(String key)  {
        return props.getProperty(key);
    }
}
