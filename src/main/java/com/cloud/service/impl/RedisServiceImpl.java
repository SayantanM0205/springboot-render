package com.cloud.service.impl;

import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.cloud.service.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class RedisServiceImpl implements RedisService{

	private RedisTemplate redisTemplate;

	@Override
	public <T> T get(String key, Class<T> dtoClass) {
		try {
			ObjectMapper mapper = new ObjectMapper();
	        mapper.registerModule(new JavaTimeModule());
	        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			String value = (String) redisTemplate.opsForValue().get(key);
			if(value != null) {
				return mapper.readValue(value, dtoClass);
			}

		}catch(Exception e) {
			log.info("Something was not right while fetching Member with Id: {}", key);
		}
		return null;
	}

	@Override
	public void set(String key, Object o, Long ttl) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			String data = mapper.writeValueAsString(o);
			redisTemplate.opsForValue().set(key, data, ttl, TimeUnit.SECONDS);
		}catch(Exception e) {
			log.info("Something did not went right while setting data for Member with Id: {}", key);
		}

	}

}
