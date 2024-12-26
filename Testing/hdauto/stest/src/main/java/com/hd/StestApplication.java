package com.hd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 업데이트 날짜가 Jpa에 반영이 된다
@SpringBootApplication
public class StestApplication {

    public static void main(String[] args) {
        SpringApplication.run(StestApplication.class, args);
    }

}
