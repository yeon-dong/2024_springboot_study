package com.sample.spring.bean1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Mycontroller {
	@Autowired
	Member member1;
	
	@Autowired
	Member member2;
	
	@RequestMapping("/")
	public @ResponseBody String root() {
		return "annotation";
	}
	
}
