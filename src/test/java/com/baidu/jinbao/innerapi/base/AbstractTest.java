package com.baidu.jinbao.innerapi.base;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@SuppressWarnings("deprecation")
public abstract class AbstractTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    @Qualifier(value = "dataSource")
    public void setDataSource(DataSource dataSource) {
        super.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

}
