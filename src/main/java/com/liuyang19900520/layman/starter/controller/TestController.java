package com.liuyang19900520.layman.starter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijiao
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("")
    public Object test() {
        return "Test1";
    }
}
