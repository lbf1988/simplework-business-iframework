package com.simplework.business.iframework.mybatis.generator;

import org.mybatis.generator.api.ShellRunner;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/08/15
 */
public class GenUtil {
    public static void generate() {
        String config = GenMapperPlugin.class.getClassLoader().getResource("mybatis/generator/generatorConfig.xml").getFile();
        String[] arg = { "-configfile", config, "-overwrite" };
        ShellRunner.main(arg);
    }
    public static void main(String[] args){
        generate();
    }
}
