package com.cloud.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cloud.entity.TrackingInfo;
import com.cloud.repository.TrackingRepository;
import com.cloud.service.GeoLocationService;

@Component
@Slf4j
@AllArgsConstructor
public class TrackingInterceptor implements HandlerInterceptor {
	
	private final GeoLocationService geoLocationService;
	private final TrackingRepository trackingRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String referer = request.getHeader("Referer");
        
        var geoLocation = geoLocationService.getGeoLocation(ipAddress);
        
        log.info("=== Tracking Info ===");
        log.info("IP Address: " + ipAddress);
        log.info("User-Agent: " + userAgent);
        log.info("Referer: " + referer);
        log.info("City: {}",geoLocation.get("city"));
        log.info("Country: {}",geoLocation.get("country"));
        
        TrackingInfo trackingInfo = new TrackingInfo();
        trackingInfo.setIpAddress(ipAddress);
        trackingInfo.setUserAgent(userAgent);
        trackingInfo.setReferer(referer);
        trackingInfo.setCity(geoLocation.get("city"));
        trackingInfo.setCountry(geoLocation.get("country"));
        
        String path = request.getRequestURI(); // e.g., /track/{sourceId}
        if (path.startsWith("/track/")) {
            trackingInfo.setSourceId(path.replace("/track/", ""));
        }
        
        trackingRepository.save(trackingInfo);

        return true; 
    }
}

