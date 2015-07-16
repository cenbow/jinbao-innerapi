package com.baidu.jinbao.innerapi.common.datasource;

import java.sql.SQLException;

/**
 * Datasource key获取接口
 * 
 * @author cgd
 * @date 2015年6月8日 下午5:38:01
 */
public interface SplitDataSourceRouter {

    /**
     * 获取目标数据源对应的key
     * 
     * @return
     * @throws SQLException
     */
    public Object getDataSourceKey() throws SQLException;

}
