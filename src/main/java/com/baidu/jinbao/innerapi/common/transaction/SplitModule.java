package com.baidu.jinbao.innerapi.common.transaction;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分库相关注解
 * 
 * @author cgd
 * @date 2015年6月2日 下午1:44:58
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SplitModule {
    /** 区分是 jinbao_common or jinbao_product **/
    String moduleName();
}
