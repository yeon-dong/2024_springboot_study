package com.sample.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.spring.dto.PageRequestDto;
import com.sample.spring.model.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

	Page<TodoEntity> search1(PageRequestDto pageRequestDto);

}