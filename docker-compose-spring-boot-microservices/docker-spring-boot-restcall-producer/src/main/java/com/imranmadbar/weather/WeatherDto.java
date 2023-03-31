package com.imranmadbar.weather;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class WeatherDto {

	private String id;
	private String country;
	private String weatherStatus;
	
	public WeatherDto(String id, String country, String weatherStatus) {
		this.id = id;
		this.country = country;
		this.weatherStatus = weatherStatus;
	}
	
	
	

}
