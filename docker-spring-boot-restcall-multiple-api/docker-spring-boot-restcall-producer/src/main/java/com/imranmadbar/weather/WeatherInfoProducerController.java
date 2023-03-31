package com.imranmadbar.weather;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherInfoProducerController {

	@Autowired
	private WeatherInfoProducerService weatherInfoProducerService;

	@GetMapping("/")
	public String index() {
		return "Welcome to weather info producer API !" + " For weather info: http://localhost:8181/get-weather-info";
	}

	@GetMapping("/get-weather-info")
	public List<WeatherDto> getWeatherInfo() {
		return weatherInfoProducerService.getWeatherInfo();
	}

}
