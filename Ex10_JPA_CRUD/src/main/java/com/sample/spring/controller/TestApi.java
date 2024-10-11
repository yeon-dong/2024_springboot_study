package com.sample.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sample.spring.entity.TestEntity;
import com.sample.spring.service.TestService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Slf4j
@RestController
public class TestApi {
	@Autowired
	private TestService testService;
	
	@GetMapping("/test")
	public void createTest() {
		testService.create("yeon-dong", 98);
	}
	

	@PostMapping("/test/create")
	public void postCreate(
			@RequestBody CreateTestRequest request
			) {
		log.info(request.getName());
		System.out.println(request.getName());
		testService.create(request.getName(), request.getAge());
	}
	
	@PutMapping("/test/update")
	public void putUpdate(@RequestParam("id") Long id, @RequestBody CreateTestRequest request) {
		testService.update(id, request.getName(), request.getAge());
	}
	
	@DeleteMapping("/test/{id}/delete")
	public void deleteUpdate(@PathVariable("id") Long id) {
		testService.delete(id);
		//@RequestParam("id") Long id 로 받아와서 할 수도 있음. 그러면 ?id=2 이런식으로 query로 가져와야 함
	}
	

//	public List<TestEntity> jsonData(){
//		return testService.findAll(); // 200 ok
//		
//	}
	@GetMapping("/test")
	public ResponseEntity<List<TestEntity>> jsonData(){
		List<TestEntity> data = testService.findAll();
		return ResponseEntity.ok(data);
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	public static class CreateTestRequest{
		private String name;
		private Integer age;
	}
}


