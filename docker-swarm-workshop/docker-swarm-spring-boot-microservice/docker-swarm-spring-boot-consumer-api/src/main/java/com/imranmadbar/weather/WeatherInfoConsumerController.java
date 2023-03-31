package com.imranmadbar.weather;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
public class WeatherInfoConsumerController {

	@Autowired
	private WeatherInfoConsumerService weatherInfoConsumerService;

	@GetMapping("/")
	public String index() {
		return "Welcome to weather info consumer API !" + " For checking weather: http://localhost:8282/check-weather";
	}

	@GetMapping("/check-weather")
	public String checkWeatherInfo() throws RestClientException, IOException {
		return weatherInfoConsumerService.getWeatherInfo();
	}

}
