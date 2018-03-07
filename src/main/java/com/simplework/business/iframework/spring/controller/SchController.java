package com.simplework.business.iframework.spring.controller;

import com.simplework.business.iframework.bo.SchBo;
import com.simplework.business.iframework.dao.SchDao;
import com.simplework.business.iframework.pojo.BasePojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/8/14
 */
public class SchController <Pojo extends BasePojo,Bo extends SchBo<Pojo, ? extends SchDao<Pojo>>> {
    protected final Logger logger = LoggerFactory.getLogger(SchController.class);

    protected Bo bo;

    /**
     * 注入相应业务层对象
     * @param bo
     */
    @Autowired
    public void setBo(Bo bo) {
        this.bo = bo;
    }


}
