package com.springsecurity.study.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jhye4
 * @date 2020/11/23 10:23
 */
@RestController
@MapperScan("com.springsecurity.study.mapper")
public class MyController {

    @GetMapping(value = "/hello")
    public String hello(){
        return "welcome jhye4";
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "hello admin";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "hello user";
    }
    @GetMapping("/db/hello")
    public String db() {
        return "hello db";
    }
}
