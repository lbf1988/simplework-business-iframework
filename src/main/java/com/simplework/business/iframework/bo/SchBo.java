package com.simplework.business.iframework.bo;

import com.simplework.business.iframework.dao.SchDao;
import com.simplework.business.iframework.mybatis.query.ConditionExample;
import com.simplework.business.iframework.pojo.BasePojo;
import com.simplework.business.iframework.pojo.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/8/14
 */
public class SchBo<T extends BasePojo, Dao extends SchDao<T>> {
    protected Dao dao;

    /**
     * 注入相应持久层对象
     * @param dao
     */
    @Autowired
    public void setDao(Dao dao){
        this.dao = dao;
    }

    public T query(Serializable id) {
        return dao.selectByPrimaryKey(id);
    }

    public int count(ConditionExample example){
        return dao.count(example);
    }

    public List<T> query(ConditionExample example){
        return dao.query(example);
    }

    public Page<T> queryPage(ConditionExample example) {
        int count = dao.count(example);
        if(count>0){
            List<T> list = dao.query(example);
            return new Page<T>(count,list);
        }
        return new Page<T>(count,null);
    }
}
