package com.simplework.business.iframework.mybatis.pagination.dialect;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/8/15
 */
public abstract class Dialect {
    public enum Type{
        MYSQL,
        ORACLE
    }
    public abstract String getLimitString(String sql,int skipResults,int maxResults);
}
