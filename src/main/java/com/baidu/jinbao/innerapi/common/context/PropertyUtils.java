package com.baidu.jinbao.innerapi.common.context;

import org.springframework.util.StringUtils;

/**
 * Property Utils
 * 
 * @author cgd
 * @date 2015年5月31日 上午10:47:35
 */
public class PropertyUtils {

    /**
     * 获取指定key的propertie，没有则返回null
     */
    public static String getProperty(String key) {
        try {
            return JinbaoPropertyConfigurer.getProperty(key);
        } catch (Exception e) {
            return key;
        }
    }

    /**
     * 获取指定key的propertie，如果为null则返回default value
     */
    public static String getProperty(String key, String defaultValue) {
        try {
            String value = JinbaoPropertyConfigurer.getProperty(key);

            return (null == value) ? defaultValue : value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 替换 eg: replacePlaceholders("${name}","test");
     */
    public static String replacePlaceholders(String key, String defaultValue) {
        try {
            if (!StringUtils.hasLength(key)) {
                return defaultValue;
            }

            String value = JinbaoPropertyConfigurer.replacePlaceholders(key);
            if (null == value) {
                return defaultValue;
            }
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
