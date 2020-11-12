package com.liuyang19900520.layman.starter.config;

import com.liuyang19900520.layman.starter.common.reids.config.LaymanRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis配置类
 * Created by macro on 2020/3/2.
 */
@EnableCaching
@Configuration
public class RedisConfig extends LaymanRedisConfig {

}
