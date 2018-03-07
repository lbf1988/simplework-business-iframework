package com.simplework.business.iframework.mybatis.pagination.dialect;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/8/15
 */
public class MysqlDialect extends Dialect {
    @Override
    public String getLimitString(String sql, int Offset, int limit) {
        return sql.trim() +" limit "+ Offset+","+limit;
    }
}
