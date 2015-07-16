package com.baidu.jinbao.innerapi.common.transaction;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.context.ThreadContext;

/**
 * 事务拦截器实现（支持分库）
 * 
 * @author cgd
 * @date 2015年6月2日 下午1:44:58
 */
public class JinbaoTransactionInterceptor extends TransactionInterceptor {

    /**
     * serial Version UID
     */
    private static final long serialVersionUID = -380588431329571203L;

    private static Logger LOGGER = Logger.getLogger(JinbaoTransactionInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            // 上层service调用如果已开启事务，则当前方法也开启
            Object dataSourceKeyObj = ThreadContext.get(PdsConstants.MODULE_DATASOURCE_KEY);
            if (dataSourceKeyObj != null) {
                return super.invoke(invocation);
            }

            // 获取Target Method信息
            Object target = getTarget(invocation);

            // 取Method上配置的Annotation信息
            SplitModule splitModule = target.getClass().getAnnotation(SplitModule.class);
            if (splitModule != null) {
                // 设置datatsource key
                String dataSouceKey = splitModule.moduleName();

                // 如果是分库的sku datasource
                if (PdsConstants.SKU_MODULE_DATASOURCE_KEY.equals(dataSouceKey)) {
                    Object[] params = invocation.getArguments();
                    if (params != null && params.length > 0) {
                        String splitDbInfo = params[0].toString();
                        String skuDsKey = PdsConstants.SKU_MODULE_DATASOURCE_KEY + "_" + splitDbInfo.split("_")[0];
                        ThreadContext.put(PdsConstants.MODULE_DATASOURCE_KEY, skuDsKey);
                    }
                } else {
                    ThreadContext.put(PdsConstants.MODULE_DATASOURCE_KEY, dataSouceKey);
                }

                return super.invoke(invocation);
            }

            // 不需要走事务控制
            return invocation.proceed();
        } catch (Throwable e) {
            LOGGER.error("JinbaoTransactionInterceptor Exception: ", e);
            throw e;
        } finally {
            // 资源清理
            ThreadContext.remove(PdsConstants.MODULE_DATASOURCE_KEY);
        }

    }

    /**
     * 获取method对应的target Object
     * */
    private Object getTarget(MethodInvocation methodInvocation) throws Exception {
        Object target = methodInvocation.getThis();
        while (AopUtils.isAopProxy(target)) {
            target = Advised.class.cast(target).getTargetSource().getTarget();
        }
        if (target == null) {
            target = methodInvocation.getThis();
        }
        return target;
    }

    /*
     * private Method getTargetMethod(MethodInvocation methodInvocation) throws Exception { Object target =
     * getTarget(methodInvocation); Class<?> targetClass = target.getClass(); Method method =
     * methodInvocation.getMethod(); String methodName = method.getName(); Class<?>[] paramClass =
     * method.getParameterTypes(); Method targetMethod = targetClass.getMethod(methodName, paramClass); return
     * targetMethod; }
     */

}
