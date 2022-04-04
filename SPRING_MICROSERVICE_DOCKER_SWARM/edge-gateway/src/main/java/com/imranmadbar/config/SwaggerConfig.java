package com.imranmadbar.config;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;


@Slf4j
@Configuration
public class SwaggerConfig {
    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    @Value("#{'${dmz.microservices}'.split(',') }")
    private List<String> excludeMicroservices;

    @Value("${server.port}")
    private int serverPort;

    String ipAddress = "localhost";

    private final DiscoveryClient discoveryClient;

    public SwaggerConfig(DiscoveryClient discoveryClient) throws UnknownHostException {
        this.discoveryClient = discoveryClient;
    }

    private List<SwaggerResource> getSwaggerResource() {

        List<SwaggerResource> resources = new ArrayList<>();

        List<String> services = discoveryClient.getServices();

        services.removeAll(excludeMicroservices);

        String regex = "^(.*?(\\beureka-\\b)[^$]*)$";
        System.out.println("Services: "+services);
        services.stream()
                .filter(x -> !x.matches(regex))
                .forEach(x -> {
                    try {
                        String url =
                                new URIBuilder()
                                        .setScheme("http")
                                        .setHost(ipAddress)
                                        .setPort(serverPort)
                                        .setPathSegments(x, "swagger-resources")
                                        .build()
                                        .toString();

                        logger.info(url);

                        logger.info("---------------SwaggerResource Call Start -----------------");
                        ResponseEntity<List<SwaggerResource>> response =
                                WebClient.builder()
                                        .build()
                                        .get()
                                        .uri(url)
                                        .retrieve()
                                        .toEntity(new ParameterizedTypeReference<List<SwaggerResource>>() {
                                        })
                                        //
                                        .map(listResponseEntity -> listResponseEntity)
                                        // .timeout(Duration.ofSeconds(3))
                                        .doOnError(z -> logger.debug(z.toString()))
                                        .block();

                        logger.info("---------------SwaggerResource Call End -----------------");

                        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                            System.out.println("SwaggerResourceList: "+response.getBody());
                            resources.addAll(
                                    response.getBody().stream()
                                            .peek(
                                                    y -> {
                                                        String location = "/" + x + y.getUrl();
                                                        y.setLocation(location);
                                                        y.setUrl(location);
                                                    })
                                            .collect(Collectors.toList()));
                        }

                    } catch (Exception e) {

                        logger.error(e.getMessage() + ": " + e.getCause());
                    }
                });

        return resources;
    }

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return this::getSwaggerResource;
    }
}
