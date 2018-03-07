package com.simplework.business.iframework.mybatis.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/08/15
 */
public class GenMapperPlugin extends PluginAdapter {
    /**
     * 获取根接口。如果有的话，会实现接口
     */
    private final static String MAPPER_CLASS = "mapperClass";

    /**
     * 根接口分隔符，多个根接口的情况
     */
    private final static String MAPPER_CLASS_SPLIT = ",";

    private Set<String> mappers = new HashSet<String>();

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        //根接口
        String mapperClass = this.properties.getProperty(MAPPER_CLASS);

        for(String mapper:mapperClass.split(MAPPER_CLASS_SPLIT)){
            this.mappers.add(mapper);
        }
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // TODO: 2017/08/15 获取实体类
        FullyQualifiedJavaType qualifiedJavaType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        GenCommentGenerator.addJavaDoc(interfaze);
        // TODO: 2017/08/15 import接口
        for(String mapper:mappers){
            interfaze.addImportedType(new FullyQualifiedJavaType(mapper));
            interfaze.addSuperInterface(new FullyQualifiedJavaType(mapper + "<" + qualifiedJavaType.getShortName() + ">"));
        }
        interfaze.addImportedType(qualifiedJavaType);
        interfaze.getMethods().clear();
        return true;
    }
}
