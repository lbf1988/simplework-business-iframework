package com.simplework.business.iframework.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/08/15
 */
public class GenXmlDaoPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement parentElement = document.getRootElement();
        // 数据库表名
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
        // 列名
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        //新增queryCnt
        parentElement.addElement(createQueryCnt(tableName));
        //新增query
        parentElement.addElement(createQuery("query",tableName));
        //新增queryWithRowbounds
        //parentElement.addElement(createQuery("queryWithRowbounds",tableName));

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    /**
     * 创建QueryCnt查询语句
     * @param tableName
     * @return
     */
    private XmlElement createQueryCnt(String tableName){
        XmlElement queryCnt = new XmlElement("select");
        queryCnt.addAttribute(new Attribute("id", "count"));
        queryCnt.addAttribute(new Attribute("parameterType", "com.simplework.business.iframework.mybatis.query.ConditionExample"));
        queryCnt.addAttribute(new Attribute("resultType", Integer.class.getName()));
        //组装queryCnt SQL映射
        StringBuilder queryCntSql = new StringBuilder();
        queryCntSql.append("select count(*) from ");
        queryCntSql.append(tableName);
        queryCntSql.append("\n");
        queryCntSql.append("\t<if test=\"_parameter != null\" >");
        queryCntSql.append("\n");
        queryCntSql.append("\t\t<include refid=\"conditionQuery.where\" />");
        queryCntSql.append("\n");
        queryCntSql.append("\t</if>");
        queryCnt.addElement(new TextElement(queryCntSql.toString()));
        return queryCnt;
    }

    private XmlElement createQuery(String id,String tableName){
        XmlElement query = new XmlElement("select");
        query.addAttribute(new Attribute("id", id));
        query.addAttribute(new Attribute("parameterType", "com.simplework.business.iframework.mybatis.query.ConditionExample"));
        query.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        //组装queryCnt SQL映射
        StringBuilder querySql = new StringBuilder();
        querySql.append("select").append("\n");
        querySql.append("\t<if test=\"distinct\" >").append("\n");
        querySql.append("\t\tdistinct").append("\n");
        querySql.append("\t</if>").append("\n");
        querySql.append("\t<include refid=\"Base_Column_List\" />").append("\n");
        querySql.append("\tfrom ").append(tableName).append("\n");
        querySql.append("\t<if test=\"_parameter != null\" >").append("\n");
        querySql.append("\t\t<include refid=\"conditionQuery.where\" />").append("\n");
        querySql.append("\t</if>").append("\n");
        querySql.append("\t<if test=\"orderByClause != null\" >").append("\n");
        querySql.append("\t\torder by ${orderByClause}").append("\n");
        querySql.append("\t</if>").append("\n");
        querySql.append("\t<if test=\"limit != null\" >").append("\n");
        querySql.append("\t\t<if test=\"offset != null\" >").append("\n");
        querySql.append("\t\t\tlimit ${offset}, ${limit}").append("\n");
        querySql.append("\t\t</if>").append("\n");
        querySql.append("\t\t<if test=\"offset == null\" >").append("\n");
        querySql.append("\t\t\tlimit ${limit}").append("\n");
        querySql.append("\t\t</if>").append("\n");
        querySql.append("\t</if>");
        query.addElement(new TextElement(querySql.toString()));
        return query;
    }
}
