package com.simplework.business.iframework.mybatis.dynamic.aspect;

import com.simplework.business.iframework.mybatis.dynamic.DBContextHolder;
import com.simplework.business.iframework.mybatis.dynamic.annotation.TargetDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/08/16
 */
public class DynamicDataSource {
    private final static Logger LogUtils = LoggerFactory.getLogger(DynamicDataSource.class);

    private TargetDataSource findDataSourceHandleAnnotation(JoinPoint joinPoint) throws ClassNotFoundException {
        Object target = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();
        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        TargetDataSource dataSource = null;
        try {
            Method method = clazz[0].getMethod(methodName,parameterTypes);
            if(method!=null && method.isAnnotationPresent(TargetDataSource.class)){
                dataSource = method.getAnnotation(TargetDataSource.class);
            }
        } catch (Exception e) {
            LogUtils.debug("选择数据源错误：method:[%s],msg:[%s]",methodName,e.getMessage());
        }
        return dataSource;
    }

    /**
     * 在dao层方法之前获取datasource对象之前在切面中指定当前线程数据源路由的key
     * @param joinPoint
     * @throws ClassNotFoundException
     */
    public void before(JoinPoint joinPoint) throws ClassNotFoundException {
        String methodName = joinPoint.getSignature().getName();
        TargetDataSource dataSource = findDataSourceHandleAnnotation(joinPoint);
        if(dataSource!=null){
            LogUtils.debug("设置数据源：["+methodName+"] - "+dataSource.value().name());
            DBContextHolder.setDbType(dataSource.value());
        }else{
            LogUtils.debug("dao["+joinPoint.getSignature().toLongString()+"] method before:["+methodName+"]");
        }
    }

    /**
     * 之後清理
     * @param point
     */
    public void after(JoinPoint point) {
        DBContextHolder.clearDBType();
    }
}
