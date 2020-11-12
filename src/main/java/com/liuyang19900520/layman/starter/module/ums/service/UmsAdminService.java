package com.liuyang19900520.layman.starter.module.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuyang19900520.layman.starter.module.ums.dto.UmsAdminParam;
import com.liuyang19900520.layman.starter.module.ums.dto.UpdateAdminPasswordParam;
import com.liuyang19900520.layman.starter.module.ums.entity.UmsAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyang19900520.layman.starter.module.ums.entity.UmsResource;
import com.liuyang19900520.layman.starter.module.ums.entity.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author Max Liu
 * @since 2020-11-06
 */
public interface UmsAdminService extends IService<UmsAdmin> {


    /***
     * 根据用户名获取后台管理员
     * @param username
     * @return 用户
     */
    UmsAdmin getAdminByUsername(String username);

    /***
     * 注册
     * @param umsAdminParam
     * @return 用户
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 刷新token的功能
     *
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);


    /**
     * 根据用户名或昵称分页查询用户
     *
     * @param keyword  用户名
     * @param pageSize 密码
     * @param pageNum  旧的token
     * @return page内容 生成的JWT的token
     */
    Page<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 登录功能
     *
     * @param id    用户名
     * @param admin 密码
     * @return 生成的JWT的token
     */
    boolean update(Long id, UmsAdmin admin);

    /**
     * 删除指定用户
     */
    boolean delete(Long id);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对于角色
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

}
