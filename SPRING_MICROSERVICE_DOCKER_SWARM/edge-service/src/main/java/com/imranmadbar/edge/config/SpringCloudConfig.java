//package com.imranmadbar.edge.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SpringCloudConfig {
//
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/STUDENT-SERVICE/**")
//                        .uri("http://localhost:8081/"))
//                .route(r -> r.path("/COURSE-SERVICE/**")
//                        .uri("http://localhost:8082/"))
//                .build();
//    }
//
//}