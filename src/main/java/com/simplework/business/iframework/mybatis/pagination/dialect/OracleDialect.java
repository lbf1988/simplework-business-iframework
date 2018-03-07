package com.simplework.business.iframework.mybatis.pagination.dialect;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/8/15
 */
public class OracleDialect extends Dialect {
    @Override
    public String getLimitString(String sql, int offset, int limit) {
        StringBuffer pagingSelect = new StringBuffer(sql.length()+100);
        pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        pagingSelect.append(sql.trim());
        pagingSelect.append(" ) row_ ) where rownum_ > ").append(offset).append(" and rownum_ <= ").append(offset + limit);
        return pagingSelect.toString();
    }
}
