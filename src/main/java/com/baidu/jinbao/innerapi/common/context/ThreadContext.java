package com.baidu.jinbao.innerapi.common.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 保存线程相关信息
 * 
 * @author cgd
 * @date 2015年6月8日 下午6:04:34
 */
public class ThreadContext {

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    private static ThreadLocal<Map<String, Object>> getThreadLocal() {
        if (threadLocal.get() == null) {
            Map<String, Object> map = new ConcurrentHashMap<String, Object>();
            threadLocal.set(map);
        }
        return threadLocal;
    }

    public static Object get(String key) {
        return getContainer().get(key);
    }

    public static Map<String, Object> getContainer() {
        return (Map<String, Object>) getThreadLocal().get();
    }

    public static boolean contains(String key) {
        return getContainer().containsKey(key);
    }

    public static boolean put(String key, Object value, boolean isForce) {
        if (contains(key) && !isForce) {
            return false;
        }
        getContainer().put(key, value);
        return true;
    }

    public static boolean put(String key, Object value) {
        return put(key, value, true);
    }

    public static boolean remove(String key) {
        return getContainer().remove(key) == null;
    }

    public static void destory() {
        getContainer().clear();
        getThreadLocal().set(null);
    }

    public static void init(Map<String, Object> container) {
        ThreadContext.destory();
        if (container == null) {
            return;
        }
        threadLocal.set(container);
    }

    public static String format2String() {
        Map<String, Object> map = threadLocal.get();
        return "ThreadContext: [" + map.toString() + "]";
    }

}
