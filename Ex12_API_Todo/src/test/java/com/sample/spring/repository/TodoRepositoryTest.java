package com.sample.spring.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sample.spring.model.TodoEntity;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoRepositoryTest {

	@Autowired
	private TodoRepository todoRepository;
	
//	@Test
//	public void testInsert() {
//		for(int i = 0; i < 97; i++ ) {
//					TodoEntity todo = TodoEntity.builder()
//				.title("title")
//				.writer("writer...")
//				.dueDate(LocalDate.of(2024, 10, 14))
//				.build();
//		
//		TodoEntity result = todoRepository.save(todo);
////		log.info(result);
//		}
//
//		
//	}
	
//	@Test
//	public void testRead() {
//		Long tno = 1L;
//		Optional<TodoEntity> result = todoRepository.findById(tno);
//		TodoEntity todo = result.orElseThrow();
//		log.info("test//////////"+todo);
//		
//	}
	
//	
	@Test
	public void testPageing(){
		Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
		Page<TodoEntity> result = todoRepository.findAll(pageable);
		log.info(result.getTotalElements());
		log.info(result.getContent());
	}
	
	
}