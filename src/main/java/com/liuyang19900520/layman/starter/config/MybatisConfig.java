package com.liuyang19900520.layman.starter.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Mybatis配置
 * </p>
 *
 * @author Max Liu
 * @since 2020/09/11
 */
@Configuration
@MapperScan("com.liuyang19900520.layman.starter.module.**.dao")
public class MybatisConfig {
}
