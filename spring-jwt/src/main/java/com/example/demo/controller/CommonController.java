package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

	@GetMapping(value="/hello")
	public List<String> helloWorld() {
		
		List<String> data = new ArrayList<>();
		data.add("some data");
		data.add("another Data");
		data.add("More Data");
		
		return data;
	}
	
}
