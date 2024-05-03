package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.dto;
import com.example.demo.test.Test;

@RestController
@RequestMapping("v1/project")
public class Controller {
	private static final Logger log = LogManager.getLogger(Controller.class);
	@Autowired
	private Test test;
			@PostMapping(value="/test2")
			public String heath(@RequestBody String user) {
			System.out.print("Hello words");
			return "hello";
			}
			@PostMapping(value="/test")
			public dto Test(@RequestBody dto data) {
			dto str =test.imageToText(data);
			String c = str.getText1();
			System.out.println("Hello words"+str.getBase());
			return str;
			}
			
			@PostMapping(value="/newtest")
			public String newTest(@RequestBody dto data) {
			String str =test.imageToText1(data);
		//	String c = str.getText1();
		//	System.out.println("Hello words"+str.getBase());
			return str;
			}

	
	

}
