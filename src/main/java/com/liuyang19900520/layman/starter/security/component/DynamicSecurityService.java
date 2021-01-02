package com.liuyang19900520.layman.starter.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * 动态权限相关业务类
 *
 * @author macro
 * @date 2020/2/7
 */
public interface DynamicSecurityService {

    /**
     * 加载资源ANT通配符和资源对应MAP
     *
     * @return 资源对应map
     */
    Map<String, ConfigAttribute> loadDataSource();
}
