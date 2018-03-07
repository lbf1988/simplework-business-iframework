# simplework-business-iframework

1.spring主从数据源配置
<bean id="dynamicDataSourceAspect" class="com.simplework.business.iframework.mybatis.dynamic.aspect.DynamicDataSource"></bean>
<!-- AOP配置-->
<aop:config proxy-target-class="true">
    <aop:aspect id="c" ref="dynamicDataSourceAspect">
        <aop:pointcut id="tx" expression="execution(* com.simplework.persistence.dao.*.*(..))"/>
        <aop:before pointcut-ref="tx" method="before"/>
        <!--<aop:before pointcut-ref="tx" method="after"/>-->
    </aop:aspect>
</aop:config>

<bean id="dataSource" class="com.simplework.business.iframework.mybatis.dynamic.DataSource">
    <!--主数据源-->
    <property name="masterDataSource" ref="masterDataSource" />
    <!--从数据源-->
    <property name="slaveDataSources">
        <list>
            <ref bean="slaveDataSource1" />
        </list>
    </property>
    <!--读数据源方式，0：随机，1：轮询-->
    <property name="slaveDataSourcePollPattern" value="1" />
    <!--默认数据源-->
    <property name="defaultTargetDataSource" ref="masterDataSource"/>
</bean>

2.mybatis generator自定义生成Model Dao
    1）generatorConfig context配置
        <plugin type="com.simplework.business.iframework.mybatis.generator.GenMapperPlugin">
            <property name="mapperClass" value="com.simplework.business.iframework.dao.CrudDao" />
        </plugin>
        <plugin type="com.simplework.business.iframework.mybatis.generator.GenXmlDaoPlugin" />
    2）javaModelGenerator增加属性
        <property name="rootClass" value="com.simplework.business.iframework.pojo.BasePojo"/>
    3）table增加属性
        <table enableSelectByExample="false" enableDeleteByExample="false"
               enableCountByExample="false" enableUpdateByExample="false" selectByExampleQueryId="false">
        </table># simplework-business-iframework
