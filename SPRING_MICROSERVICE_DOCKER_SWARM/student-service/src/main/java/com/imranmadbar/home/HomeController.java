package com.imranmadbar.home;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class HomeController {


    @Value("${simple.envVal}")
    private String simpleEnvVal;

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping(value="/env-val")
    public String checkEnv(){
        logger.info("SimpleEnvVal: "+simpleEnvVal);
        return "SimpleEnvVal: "+simpleEnvVal;
    }

    @GetMapping(value="/")
    public String appIndex(){
        logger.info("Welcome to Student Application");
        return "Welcome to Student Application";
    }

    @GetMapping(value="/home")
    public String appHome(){
        logger.info("Welcome to Student Application Home");
        return "Welcome to Student Application Home";
    }

}
