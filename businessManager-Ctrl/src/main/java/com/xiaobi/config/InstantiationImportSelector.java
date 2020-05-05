package com.xiaobi.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//App 里面的@SpringBootApplication.@EnableAutoConfiguration.@Import({AutoConfigurationImportSelector.class})
//就是用到ImportSelector这个类来实现Spring的自动装配
//Spring通过扫描App类路劲下所有的类名来初始化类
public class InstantiationImportSelector implements ImportSelector {
    /**
     * 返回一个字符串数组 里面包含了需要被Spring容器初始化的类全路径
     * @param annotationMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.xiaobi.config.AppConfig"};
    }
}
