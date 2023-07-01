package com.icedragongame.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *     project: snow_dragonGame blogSystem
 *
 *  该类名称为:
 *     <name>
 *         MybatisPlusConfig
 *
 *  该类作用为:
 *   <effect>
 *       configuration for mp
 *
 *@author gengxuelong
 *
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *         mybatisPlusInterceptor
     *  该方法作用为:
     *   <effect>
     *       配置分页拦截器,可以直接使用mp的Page类进行分页查询
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
