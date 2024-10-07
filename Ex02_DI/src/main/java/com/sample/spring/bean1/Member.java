package com.sample.spring.bean1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Member {
	@Value("홍길동")
	private String name;
	@Value("도사")
	private String nickname;
	
	public Member() {}
	public Member(String name, String nickname) {
		this.name = name;
		this.nickname = nickname;
	}
}
