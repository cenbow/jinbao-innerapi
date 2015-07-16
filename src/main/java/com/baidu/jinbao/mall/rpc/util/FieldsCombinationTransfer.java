package com.baidu.jinbao.mall.rpc.util;

import java.util.List;

public class FieldsCombinationTransfer {
    public static String fieldsTransferToFieldsCombination(List<String> fieldList) {
        if (fieldList == null) {
            throw new RuntimeException("Fields is Null!");
        }
        // 传入字符串类似"sku_info#id&name&addTime";
        String fieldsCombination = "";
        if (fieldList.get(0).equals("*")) {
            fieldsCombination = "*";
        } else {
            for (int i = 0; i < fieldList.size(); i++) {
                String[] tableInfo = fieldList.get(i).split("#");
                if (tableInfo.length != 2) {
                    throw new RuntimeException("Every Field should like sku_info#id&name&addTime!");
                }
                String[] fieldsInfo = tableInfo[1].split("&");
                for (int j = 0; j < fieldsInfo.length; j++) {
                    if ((i == 0) && (j == 0)) {
                        fieldsCombination = tableInfo[0] + "." + fieldsInfo[j];
                    } else {
                        fieldsCombination += "," + tableInfo[0] + "." + fieldsInfo[j];
                    }
                }
            }
        }
        return fieldsCombination;
    }
}
