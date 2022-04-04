//package com.imranmadbar.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.info.BuildProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.Collections;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Autowired
//    private  BuildProperties buildProperties;
//
//
//    private ApiInfo apiInfo() {
//
//        return new ApiInfo("Student Service RESTful APIs",
//                "APIs for Student Service",
//                "1.0.0.RELEASE",
//                "Terms of service",
//                new Contact("MD IMRAN HOSSAIN", "https://www.linkedin.com/in/imranmadbarlkin/","imranmadbar@gmail.com"),
//                "License of API",
//                "API license URL",
//                Collections.emptyList());
//    }
//
//    @Bean
//    public Docket api() {
//        String appName = this.buildProperties.getName();
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .groupName(appName+ " " + this.buildProperties.getVersion())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//}