package com.simplework.business.iframework.bo;

import com.simplework.business.iframework.dao.CrudDao;
import com.simplework.business.iframework.pojo.BasePojo;

import java.io.Serializable;

/**
 * @Author Brant Liu
 * @Desc TODO 业务层增、删、改、查通用父类
 * @MailTo lbf1988@qq.com
 * @Date 2017/8/14
 */
public class CrudBo<T extends BasePojo,Dao extends CrudDao<T>> extends SchBo<T, Dao> {
    /**
     * 新增数据
     * @param pojo
     * @return
     */
    public int save(T pojo){
        return dao.insertSelective(pojo);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    public int delete(Serializable id){
        return dao.deleteByPrimaryKey(id);
    }

    /**
     * 更新数据
     * @param pojo
     * @return
     */
    public int update(T pojo){
        return dao.updateByPrimaryKeySelective(pojo);
    }
}
