package com.simplework.business.iframework.mybatis.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import java.util.List;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/08/17
 */
public class GenReplaceMapperToDaoPlugin extends PluginAdapter {
    /**
     * 自定义后缀  UserMapper==> UserDao
     */
    private String mapperSuffix;
    private static final String defaultSuffix = "Mapper";
    @Override
    public boolean validate(List<String> list) {
        mapperSuffix = this.properties.getProperty("mapperSuffix","Dao");
        return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        String mapperType=introspectedTable.getMyBatis3JavaMapperType();
        introspectedTable.setMyBatis3JavaMapperType(mapperType.replaceFirst(defaultSuffix,mapperSuffix));

        String xmlMapper= introspectedTable.getMyBatis3XmlMapperFileName();
        introspectedTable.setMyBatis3XmlMapperFileName(xmlMapper.replaceFirst(defaultSuffix,mapperSuffix));
    }
}
