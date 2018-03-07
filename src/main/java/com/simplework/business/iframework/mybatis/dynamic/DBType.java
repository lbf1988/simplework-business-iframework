package com.simplework.business.iframework.mybatis.dynamic;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/08/16
 */
public enum DBType {
    /**
     * 主表、主要用于写入数据
     */
    master,
    /**
     * 从表、主要用于读取数据
     */
    slave;
}
