package com.baidu.jinbao.innerapi.common.datasource;

import java.sql.SQLException;

import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.context.ThreadContext;

/**
 * Datasource key获取实现类
 * 
 * @author cgd
 * @date 2015年6月8日 下午5:39:40
 */
public class JinbaoSplitDataSourceRouter implements SplitDataSourceRouter {

    /**
     * 默认的数据源key
     */
    private String defaultDataSourceKey;

    @Override
    public Object getDataSourceKey() throws SQLException {
        String dsKey = (String) ThreadContext.get(PdsConstants.MODULE_DATASOURCE_KEY);
        return dsKey != null ? dsKey : getDefaultDataSourceKey();
    }

    // ------------------------------------------------
    public String getDefaultDataSourceKey() {
        return defaultDataSourceKey;
    }

    public void setDefaultDataSourceKey(String defaultDataSourceKey) {
        this.defaultDataSourceKey = defaultDataSourceKey;
    }

}
