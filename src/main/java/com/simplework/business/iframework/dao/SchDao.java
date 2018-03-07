package com.simplework.business.iframework.dao;

import com.simplework.business.iframework.mybatis.dynamic.DBType;
import com.simplework.business.iframework.mybatis.dynamic.annotation.TargetDataSource;
import com.simplework.business.iframework.mybatis.query.ConditionExample;
import com.simplework.business.iframework.pojo.BasePojo;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Brant Liu
 * @Desc TODO 持久层查询通用父接口,适用仅需查询功能需求
 * @MailTo lbf1988@qq.com
 * @Date 2017/8/14
 */
public interface SchDao<T extends BasePojo> {
    /**
     * 根据主键ID获得数据库对应记录
     * @param id
     * @return
     */
    @TargetDataSource(value = DBType.slave)
    T selectByPrimaryKey(Serializable id);

    /**
     * 根据条件查询记录条数
     * @param example
     * @return
     */
    @TargetDataSource(value = DBType.slave)
    int count(ConditionExample example);

    /**
     * 根据自定义ConditionExample查询
     * @param example
     * @return
     */
    @TargetDataSource(value = DBType.slave)
    List<T> query(ConditionExample example);
}
