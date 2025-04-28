package com.cloud.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cloud.service.GeoLocationService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GeoLocationServiceImpl implements GeoLocationService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${geolocation.api.url}")
	private String gepApiUri;
	
	public Map<String, String> getGeoLocation(String ipAddress) {
		try {
			String url = gepApiUri + ipAddress;
			log.info("URL created.........:{}",url);
			Map<String, String> response = restTemplate.getForObject(url, HashMap.class);
			Map<String, String> geoLocation = new HashMap<>();
			geoLocation.put("country", (String) response.get("country"));
			geoLocation.put("city", (String) response.get("city"));
			return geoLocation;
		}catch(Exception e) {
			log.info("Something did not went right while fetching location of ip:{}", ipAddress);
			return new HashMap<>();
		}
	}

}
