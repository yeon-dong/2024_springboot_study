package com.hd;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
@DisplayName("Test Sequence")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //이걸 사용해야 order를 사용할 수 있음
//@TestMethodOrder(MethodOrderer.MethodName.class) //메소드 명칭에 따라서 실행 순서를 정하게 됨
@TestMethodOrder(MethodOrderer.Random.class) //이 어노테이션을 사용하면 테스트 메소드가 랜덤한 순서로 실행되도록 설정할 수 있습니다.
public class Junit_Seq {
    @BeforeAll
    public static void beforeAll(){
        log.info("Before All");
    }

    @AfterAll
    public static void afterAll(){
        log.info("After All");
    }

    @BeforeEach
    public void beforeEach(){
        log.info("Before Each");
    }

    @AfterEach
    public void afterEach(){
        log.info("After Each");
    }

    @Test
    @DisplayName("Test1 정상케이스")
    @Order(3)
    public void test1() {
        log.info("Test1");
    }

    @Test
    @DisplayName("Test2 정상케이스")
    @Order(1)
    public void test2() {
        log.info("Test2");
    }

    @Test
    @DisplayName("Test3 정상케이스")
    @Order(2)
    public void test3() {
        log.info("Test3");
    }
}
