package com.future.userweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    @Qualifier("eurekaClient")
    EurekaClient eurekaClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @RequestMapping("getClients")
    public String getClients(){
        List<String> services = discoveryClient.getServices();
        services.forEach(service -> {
            log.info("client : {}", service);
        });

        return JSONObject.toJSONString(services);

    }

    @RequestMapping("getInstances")
    public Object getInstances(){
        return discoveryClient.getInstances("user-api");
    }

    @RequestMapping("getRPC")
    public Object getRPC(){
//        List<InstanceInfo> instances = eurekaClient.getInstancesById("DESKTOP-UQOS3GQ:user-api:8001");
        List<InstanceInfo> instances = eurekaClient.getInstancesByVipAddress("user-api", false);
        instances.forEach(instance -> {
            log.info("user-api : {}", instance);
        });
        if (instances.size() > 0) {
            InstanceInfo instanceInfo = instances.get(0);
            InstanceInfo.InstanceStatus status = instanceInfo.getStatus();
            if (status == InstanceInfo.InstanceStatus.UP) {
                log.info("instance : http://{}:{}", instanceInfo.getHostName(), instanceInfo.getPort());
                String url = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/index";
                RestTemplate restTemplate = new RestTemplate();
                String response = restTemplate.getForObject(url, String.class);
                log.info("url : {} --> response : {}", url, response);
            }

        }
        return instances;
    }

    @RequestMapping("ribbonCall")
    public Object ribbonCall(){
        ServiceInstance instance = loadBalancerClient.choose("user-api");
        log.info("lb choose instance : {}", instance);
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/index";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("url : {} --> response : {}", url, response);
        return instance;
    }

    @RequestMapping("modify")
    public String modify(){
        return "modify";
    }
}
