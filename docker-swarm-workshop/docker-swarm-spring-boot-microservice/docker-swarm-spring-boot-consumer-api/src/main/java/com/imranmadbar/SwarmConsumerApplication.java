package com.imranmadbar;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestClientException;

import com.imranmadbar.weather.WeatherInfoConsumerService;

@SpringBootApplication
public class SwarmConsumerApplication {

	public static void main(String[] args) throws RestClientException, IOException {
		ApplicationContext appContx = SpringApplication.run(SwarmConsumerApplication.class, args);
		System.out.println("Swarm Consumer Application Run Successfully Done !");
		System.out.println("Start rest calling to swarm producer service....");
		WeatherInfoConsumerService weatherInfoConsumerService = appContx.getBean(WeatherInfoConsumerService.class);
		weatherInfoConsumerService.getWeatherInfo();

	}

}
