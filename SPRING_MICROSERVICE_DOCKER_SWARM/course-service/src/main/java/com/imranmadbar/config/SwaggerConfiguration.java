package com.imranmadbar.config;


import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfiguration {


    private final BuildProperties buildProperties;

    public SwaggerConfiguration(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        String buildProStr = this.buildProperties.getName();
        return docket
                .groupName(buildProStr + " " + this.buildProperties.getVersion())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(
                        (new ApiInfoBuilder())
                                .title(this.buildProperties.getName() + " API")
                                .description(this.buildProperties.getName() + " REST API")
                                .version(this.buildProperties.getVersion())
                                .license("©  imranmadbar@gmail.com")
                                .licenseUrl("https://www.linkedin.com/in/imranmadbarlkin/")
                                .contact(
                                        new Contact(
                                                "MD IMRAN HOSSAIN",
                                                "https://www.linkedin.com/in/imranmadbarlkin/",
                                                "imranmadbar@gmail.com"))
                                .build());
    }






}
