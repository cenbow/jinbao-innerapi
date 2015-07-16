package com.baidu.jinbao.innerapi.sku.util;

public class SignatureUtil {
    public static int SKU_INFO = 0;
    public static int SKU_ATTRIBUTE = 1;
    public static int SKU_DESCRIPTION = 2;
    public static int SKU_LEVEL = 3;
    public static String DEFAULT_VALUE = "___";
    
    public static String parseSignature(String signature, int tableId) {
        if (signature == null || signature.isEmpty()) {
            return "";
        }
        // 获取表对应的签名
        return signature.split("_", -1)[tableId];
    }

    public static String setSignature(String signature, String hashCode, int tableId) {
        if (signature == null || signature.isEmpty()) {
            signature = DEFAULT_VALUE;
        }
        // 设置某个表的签名到对应字段
        String ret = "";
        String[] splitArray = signature.split("_", -1);
        for (int i = 0; i <= 3; ++i) {
            if (i != tableId) {
                ret += splitArray[i];
            } else {
                ret += hashCode;
            }
            if (i != 3) {
                ret += "_";
            }
        }
        return ret;
    }
}
