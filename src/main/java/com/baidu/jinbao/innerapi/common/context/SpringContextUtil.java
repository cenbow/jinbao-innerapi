package com.baidu.jinbao.innerapi.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring 容器工具类
 * 
 * @author cgd
 * @date 2015年5月31日 上午10:27:55
 */
public class SpringContextUtil {

    // spring 容器初始化
    private static ApplicationContext applicationContext;
    
    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        if (context != null) {
            applicationContext = context;
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据Bean Name查找
     * */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 根据Bean Type查找
     * */
    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(requiredType);
    }
}
