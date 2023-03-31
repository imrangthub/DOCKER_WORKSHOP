package com.imranmadbar;


import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestClientException;

import com.imranmadbar.weather.WeatherInfoConsumerService;

@SpringBootApplication
public class RestCallConsumerApplication {

	public static void main(String[] args) throws RestClientException, IOException {
		ApplicationContext appContx = SpringApplication.run(RestCallConsumerApplication.class, args);
		System.out.println("Consumer Application Run Successfully Done !");
		System.out.println("Start rest calling to producer service....");
		WeatherInfoConsumerService weatherInfoConsumerService = appContx.getBean(WeatherInfoConsumerService.class);
		weatherInfoConsumerService.getWeatherInfo();

	}

}