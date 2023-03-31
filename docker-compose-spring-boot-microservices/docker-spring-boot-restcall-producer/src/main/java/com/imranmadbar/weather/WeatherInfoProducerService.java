package com.imranmadbar.weather;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class WeatherInfoProducerService {
	
	
	public List<WeatherDto> getWeatherInfo() {
		
		List<WeatherDto> weatherData = Arrays.asList(
				new WeatherDto("BI1001", "Bangladesh", "Good"),
				new WeatherDto("UI1002", "USA", "Not Good"),
				new WeatherDto("CI1003", "Canada", "Good")
				);
		
		return weatherData;
		
	}
	
	
	

}
