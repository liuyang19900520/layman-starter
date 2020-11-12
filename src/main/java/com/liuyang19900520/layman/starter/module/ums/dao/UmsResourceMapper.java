package com.liuyang19900520.layman.starter.module.ums.dao;

import com.liuyang19900520.layman.starter.module.ums.entity.UmsResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 后台资源表 Mapper 接口
 * </p>
 *
 * @author Max Liu
 * @since 2020-11-06
 */
@Repository
public interface UmsResourceMapper extends BaseMapper<UmsResource> {

    /**
     * 获取用户所有可访问资源
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 根据角色ID获取资源
     */
    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
