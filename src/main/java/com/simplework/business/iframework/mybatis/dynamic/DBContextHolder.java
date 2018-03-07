package com.simplework.business.iframework.mybatis.dynamic;

/**
 * @Author Brant Liu
 * @Desc TODO 存放DataSource
 * @MailTo lbf1988@qq.com
 * @Date 2017/08/16
 */
public class DBContextHolder {
    /**
     * 线程
     */
    private static final ThreadLocal<DBType> contextHolder = new ThreadLocal<DBType>();
    /**
     * 默认选择数据库
     */
    private static final DBType DEFAUL_DB_TYPE = DBType.master;

    private DBContextHolder() {
    }

    public static void setDbType(DBType dbType){
        contextHolder.set(dbType);
    }

    public static DBType getDbType(){
        return contextHolder.get();
    }

    public static void clearDBType(){
        contextHolder.remove();
    }
}
