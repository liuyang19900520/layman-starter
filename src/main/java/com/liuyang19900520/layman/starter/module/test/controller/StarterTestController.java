package com.liuyang19900520.layman.starter.module.test.controller;


import com.liuyang19900520.layman.starter.module.test.service.StarterTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试用 前端控制器
 * </p>
 *
 * @author Max Liu
 * @since 2020-09-11
 */
@RestController
@RequestMapping("/test/starterTest")
public class StarterTestController {

    @Autowired
    StarterTestService starterTestService;

    @GetMapping("")
    public Object test() {
        return starterTestService.list();
    }

}

