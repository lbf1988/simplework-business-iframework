package com.simplework.business.iframework.mybatis.generator;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/08/17
 */
public class GenServicePlugin extends PluginAdapter {
    private String pojoPackage;
    private String servicePackage;
    private String serviceTargetProject;
    private String serviceSuffix;


    @Override
    public boolean validate(List<String> list) {
        pojoPackage = properties.getProperty("pojoPackage");
        servicePackage = properties.getProperty("targetPackage");
        serviceTargetProject = properties.getProperty("targetProject");
        serviceSuffix = properties.getProperty("serviceSuffix","Service");
        boolean valid = StringUtility.stringHasValue(servicePackage)
                && StringUtility.stringHasValue(serviceTargetProject)
                && StringUtility.stringHasValue(pojoPackage);
        return valid;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> files = new ArrayList<GeneratedJavaFile>();
        String table = introspectedTable.getBaseRecordType();
        String tableName = table.replaceAll(this.pojoPackage + ".", "");
        FullyQualifiedJavaType serviceInterfaceType = new FullyQualifiedJavaType(this.servicePackage + "." + tableName + this.serviceSuffix);
        Interface rpcInterface = new Interface(serviceInterfaceType);
        rpcInterface.setVisibility(JavaVisibility.PUBLIC);

        GenCommentGenerator.addJavaDoc(rpcInterface);

        JavaFormatter javaFormatter = new DefaultJavaFormatter();
        javaFormatter.setContext(context);

        GeneratedJavaFile file = new GeneratedJavaFile(rpcInterface,serviceTargetProject,javaFormatter);
        files.add(file);

        return files;
    }
}
