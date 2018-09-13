package com.springcloud.wsh.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.wsh.service.HystrixService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: HystrixController
 * @ProjectName springcloud_hystrix_dashboard
 * @Description: 测试
 * @Author WeiShiHuai
 * @Date 2018/9/13 15:48
 */
@RestController
public class HystrixController2 {

    private static Logger logger = LoggerFactory.getLogger(HystrixController2.class);

    @Autowired
    private HystrixService2 hystrixService;

    @RequestMapping("/getInfo")
    public String getInfo(@RequestParam("name") String name) {
        return hystrixService.getInfo(name);
    }

    @RequestMapping("/hello")
    @HystrixCommand(fallbackMethod = "hiError")
    public String hello(@RequestParam String name) {
        return "hello, " + name;
    }

    public String helloError(String name) {
        return "hello, " + name + ",sorry,error!";
    }
}
