package com.baidu.jinbao.innerapi.common.datasource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源实现
 * 
 * @author cgd
 * @date 2015年6月8日 下午5:35:26
 */
public class SplitDataSource extends AbstractRoutingDataSource {

    private static final Logger LOG = Logger.getLogger(SplitDataSource.class);

    private SplitDataSourceRouter splitDataSourceRouter;

    /**
     * 获取目标数据源对应的instanceId, 获取不到数据源返回默认数据源
     * 
     * @return 数据源对应instanceId
     */
    @Override
    protected Object determineCurrentLookupKey() {
        try {
            return splitDataSourceRouter.getDataSourceKey();
        } catch (Throwable e) {
            LOG.error("can not obtain datasourcekey!", e);
            throw new RuntimeException(e);
        }
    }

    public void setSplitDataSourceRouter(SplitDataSourceRouter dataSourceRouter) {
        this.splitDataSourceRouter = dataSourceRouter;
    }
}
