package com.cloud.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CloudNativeController {
	
	@GetMapping("/status")
	public String checkStatus(@RequestParam String name) {
		return "Hello "+name+"! I'm working just fine";
	}

}
