package com.springcloud.wsh.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Title: HystrixService
 * @ProjectName springcloud_hystrix_dashboard
 * @Description: 实现Hystrix断路器
 * @Author WeiShiHuai
 * @Date 2018/9/13 15:52
 */
@Service
public class HystrixService2 {

    private static Logger logger = LoggerFactory.getLogger(HystrixService2.class);

    @Autowired
    RestTemplate restTemplate;

    //@HystrixCommand注解主要对getInfo()开启熔断器的功能，并指定fallbackMethod熔断方法(服务不可用时执行熔断方法)
    @HystrixCommand(fallbackMethod = "getInfoErrorFallBack")
    public String getInfo(String name) {
        String info = restTemplate.getForObject("http://hystrix-service/getInfo?name={name}", String.class, name);
        logger.info(info);
        return info;
    }

    /**
     * Hystrix熔断方法（即调用失败回调方法）
     *
     * @param name
     * @return
     */
    public String getInfoErrorFallBack(String name) {
        return "sorry, " + name + ", the hystrix service is not available! ";
    }

}
