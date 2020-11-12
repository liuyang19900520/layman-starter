package com.liuyang19900520.layman.starter.module.ums.controller;


import com.liuyang19900520.layman.starter.common.api.AResultCode;
import com.liuyang19900520.layman.starter.common.api.CommonResult;
import com.liuyang19900520.layman.starter.common.exception.AuthException;
import com.liuyang19900520.layman.starter.module.ums.dto.UmsAdminLoginParam;
import com.liuyang19900520.layman.starter.module.ums.service.UmsAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author Max Liu
 * @since 2020-11-06
 */
@RestController
@RequestMapping("/ums/admin")
public class UmsAdminController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminService adminService;

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            throw new AuthException("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

}

