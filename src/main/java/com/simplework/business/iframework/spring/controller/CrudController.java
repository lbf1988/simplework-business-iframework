package com.simplework.business.iframework.spring.controller;

import com.simplework.business.iframework.bo.CrudBo;
import com.simplework.business.iframework.dao.CrudDao;
import com.simplework.business.iframework.pojo.BasePojo;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/8/14
 */
public class CrudController<Pojo extends BasePojo, Bo extends CrudBo<Pojo, ? extends CrudDao<Pojo>>>
        extends SchController<Pojo,Bo> {
}
