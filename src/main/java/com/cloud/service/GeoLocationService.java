package com.cloud.service;

import java.util.Map;

public interface GeoLocationService {

	Map<String,String> getGeoLocation(String ipAddress);
}
