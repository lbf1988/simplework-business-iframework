package com.simplework.business.iframework.mybatis.generator;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
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
public class GenServiceImplPlugin extends PluginAdapter {
    /**
     * Pojo类包名
     */
    private String pojoPackage;
    private String rpcPackage;
    private String rpcServiceSuffix;
    private String servicePackage;
    private String serviceTargetProject;
    private String superClass;
    private String serviceSuffix;
    private String daoSuffix;
    private FullyQualifiedJavaType serviceType;
    private FullyQualifiedJavaType pojoType;
    private FullyQualifiedJavaType daoType;
    private FullyQualifiedJavaType rpcType;


    @Override
    public boolean validate(List<String> list) {
        pojoPackage = properties.getProperty("pojoPackage");
        rpcPackage = properties.getProperty("rpcPackage");
        servicePackage = properties.getProperty("targetPackage");
        serviceTargetProject = properties.getProperty("targetProject");
        serviceSuffix = properties.getProperty("serviceSuffix","Bo");
        daoSuffix = properties.getProperty("daoSuffix","Dao");
        rpcServiceSuffix = properties.getProperty("rpcServiceSuffix","Service");
        superClass = properties.getProperty("superClass","com.simplework.business.iframework.bo.CrudBo");
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

        serviceType = new FullyQualifiedJavaType(this.servicePackage + "." + tableName + this.serviceSuffix);

        pojoType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        if(StringUtility.stringHasValue(this.rpcPackage)){
            rpcType = new FullyQualifiedJavaType(this.rpcPackage + "." + tableName + this.rpcServiceSuffix);
        }

        daoType = new FullyQualifiedJavaType(introspectedTable.getDAOInterfaceType().replaceFirst("DAO",daoSuffix));

        TopLevelClass topLevelClass = new TopLevelClass(serviceType);
        GenCommentGenerator.addJavaDoc(topLevelClass);

        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        //Import package
        topLevelClass.addImportedType(new FullyQualifiedJavaType(this.superClass));
        topLevelClass.addImportedType(pojoType);
        topLevelClass.addImportedType(daoType);
        if(StringUtility.stringHasValue(this.rpcPackage)){
            topLevelClass.addImportedType(rpcType);
            topLevelClass.addSuperInterface(rpcType);
        }
        topLevelClass.setSuperClass(new FullyQualifiedJavaType(superClass+"<"+pojoType.getShortName()+","+daoType.getShortName()+">"));
        //Add springMvc annotation
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
        topLevelClass.addAnnotation("@Service");

        JavaFormatter javaFormatter = new DefaultJavaFormatter();
        javaFormatter.setContext(context);

        GeneratedJavaFile file = new GeneratedJavaFile(topLevelClass,serviceTargetProject,javaFormatter);
        files.add(file);

        return files;
    }
}
