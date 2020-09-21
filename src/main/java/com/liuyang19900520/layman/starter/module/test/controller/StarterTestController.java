package com.liuyang19900520.layman.starter.module.test.controller;


import com.liuyang19900520.layman.starter.common.api.CommonResult;
import com.liuyang19900520.layman.starter.common.json.annotation.LaymanJson;
import com.liuyang19900520.layman.starter.common.json.annotation.LaymanJsons;
import com.liuyang19900520.layman.starter.module.test.entity.StarterTest;
import com.liuyang19900520.layman.starter.module.test.entity.Test;
import com.liuyang19900520.layman.starter.module.test.service.StarterTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 测试用 前端控制器
 * </p>
 *
 * @author Max Liu
 * @since 2020-09-13
 */
@RestController
@RequestMapping("/test/starterTest")
@Api(tags = "用户管理相关接口")
public class StarterTestController {
    @Autowired
    StarterTestService starterTestService;

    @GetMapping("/users")
    @ApiOperation("显示用户一览")
    public CommonResult<List<StarterTest>> users() {
        return CommonResult.success(starterTestService.list());
    }

    @GetMapping("/users/{id}")
    @ApiOperation("显示当前用户")
    @LaymanJsons({@LaymanJson(type = Test.class, include = "age,name"), @LaymanJson(type = StarterTest.class, include = "id,username,test")})
    public CommonResult<StarterTest> user(@PathVariable Long id) {
        StarterTest byId = starterTestService.getById(id);
        Test test = new Test();
        test.setPoint("234");
        test.setAge("2323");
        test.setName(null);
        byId.setTest(test);
        return CommonResult.success(byId);
    }

    @PostMapping("/users/")
    @ApiOperation("添加用户")
    public CommonResult postUser(@RequestBody StarterTest starterTest) {
        return CommonResult.success(starterTestService.save(starterTest));
    }

    @PutMapping("/users/{id}")
    @ApiOperation("修改用户")
    public CommonResult putUser(@RequestBody StarterTest starterTest, @PathVariable Long id) {
        starterTest.setId(id);
        return CommonResult.success(starterTestService.updateById(starterTest));
    }

    @DeleteMapping("/users/{id}")
    @ApiOperation("删除用户")
    public CommonResult deleteUser(@PathVariable String id) {
        return CommonResult.success(starterTestService.removeById(Long.parseLong(id)));
    }

}

