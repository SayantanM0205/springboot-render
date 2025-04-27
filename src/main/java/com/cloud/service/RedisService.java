package com.cloud.service;

public interface RedisService {
	<T> T get(String key, Class<T> DtoClass);
	
	void set(String key, Object o, Long ttl);
}
