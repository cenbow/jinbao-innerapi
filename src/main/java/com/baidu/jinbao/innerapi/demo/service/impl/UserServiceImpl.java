package com.baidu.jinbao.innerapi.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.common.context.PropertyUtils;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.demo.service.UserService;

/**
 * UserServiceImpl
 * 
 * @author cgd
 * @date 2015年5月30日 下午9:48:21
 */
@Service
@SplitModule(moduleName = "jinbao_common")
public class UserServiceImpl implements UserService {

    
    public String getCurrentUserInfo() {
        String tmp = PropertyUtils.getProperty("module_name");
        System.out.println(tmp);
        return "shine";
    }

    public void updateProductInfo(String splictDBMark, List<Object> data) {
        // 相关数据处理
    }

}
