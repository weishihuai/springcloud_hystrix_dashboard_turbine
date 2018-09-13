package com.springcloud.wsh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: HystrixServiceController
 * @Description: 服务提供者
 * @Author WeiShiHuai
 * @Date 2018/9/11 9:31
 */
@RestController
public class HystrixServiceController {

    private static Logger logger = LoggerFactory.getLogger(HystrixServiceController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/getInfo")
    public String getInfo(@RequestParam("name") String name) {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        String host = serviceInstance.getHost();
        Integer port = serviceInstance.getPort();
        String info = "hello, name = " + name + ", host = " + host + ", port = " + port;
        logger.info(info);
        return info;
    }

}
