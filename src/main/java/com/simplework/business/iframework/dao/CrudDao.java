package com.simplework.business.iframework.dao;

import com.simplework.business.iframework.mybatis.dynamic.DBType;
import com.simplework.business.iframework.mybatis.dynamic.annotation.TargetDataSource;
import com.simplework.business.iframework.pojo.BasePojo;

import java.io.Serializable;

/**
 * @Author Brant Liu
 * @Desc TODO 持久层增、删、改、查通用父接口
 * @MailTo lbf1988@qq.com
 * @Date 2017/8/14
 */
public interface CrudDao<T extends BasePojo> extends SchDao<T>  {
    /**
     * 新增记录
     * @param pojo
     * @return
     */
    @TargetDataSource(value = DBType.master)
    int insertSelective(T pojo);

    /**
     * 删除记录
     * @param id
     * @return
     */
    @TargetDataSource(value = DBType.master)
    int deleteByPrimaryKey(Serializable id);

    /**
     * 更新记录
     * @param pojo
     * @return
     */
    @TargetDataSource(value = DBType.master)
    int updateByPrimaryKeySelective(T pojo);
}
