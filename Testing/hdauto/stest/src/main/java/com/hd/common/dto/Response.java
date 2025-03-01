package com.hd.common.dto;

import com.hd.common.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;


@Slf4j
@Component
public class Response {

    @Getter
    @Builder
    public static class Body {
        private int state;
        private String result;
        private String message;
        private Object data;
        private Object error;
        private ErrorCode errorCode;
    }

    public   ResponseEntity<?> success(Object data, String msg, HttpStatus status) {
        Body body = Body.builder()
                .state(status.value())
                .data(data)
                .result("success")
                .message(msg)
                .error(Collections.emptyList())
                .build();

        return ResponseEntity.ok(body);
    }

    /**
     * <p> 메세지만 가진 성공 응답을 반환한다.</p>
     * <pre>
     *     {
     *         "state" : 200,
     *         "result" : success,
     *         "message" : message,
     *         "data" : [],
     *         "error" : []
     *     }
     * </pre>
     *
     * @param msg 응답 바디 message 필드에 포함될 정보
     * @return 응답 객체
     */
    public    ResponseEntity<?> success(String msg) {
        return success(Collections.emptyList(), msg, HttpStatus.OK);
    }

    /**
     * <p> 데이터만 가진 성공 응답을 반환한다.</p>
     * <pre>
     *     {
     *         "state" : 200,
     *         "result" : success,
     *         "message" : null,
     *         "data" : [{data1}, {data2}...],
     *         "error" : []
     *     }
     * </pre>
     *
     * @param data 응답 바디 data 필드에 포함될 정보
     * @return 응답 객체
     */
    public    ResponseEntity<?> success(Object data) {
        return success(data, null, HttpStatus.OK);
    }
    public    ResponseEntity<?> successCreate(Object data) {
        return success(data, null, HttpStatus.CREATED);
    }

    /**
     * <p> 성공 응답만 반환한다. </p>
     * <pre>
     *     {
     *         "state" : 200,
     *         "result" : success,
     *         "message" : null,
     *         "data" : [],
     *         "error" : []
     *     }
     * </pre>
     *
     * @return 응답 객체
     */
    public   ResponseEntity<?> success() {
        return success(Collections.emptyList(), null, HttpStatus.OK);
    }

//    /**
//     * <p> 단순 메시지를 가진 실패 응답을 반환한다. </p>
//     * <pre>
//     *     {
//     *         "state" : HttpStatus Code,
//     *         "result" : fail,
//     *         "message" : message,
//     *         "data" : [],
//     *         "error" : [{error1}, {error2}...]
//     *     }
//     * </pre>
//     *
//     * @param msg 응답 바디 message 필드에 포함될 정보
//     * @param status 응답 바디 status 필드에 포함될 응답 상태 코드
//     * @return 응답 객체
//     */
    public   ResponseEntity<?> fail(Object data, String msg, HttpStatus status) {
        Body body = Body.builder()
                .state(status.value())
                .data(data)
                .result("fail")
                .message(msg)
                .error(Collections.emptyList())
                .build();
        return ResponseEntity.ok(body);
    }
    public    ResponseEntity<?> fail(String msg, HttpStatus status) {
        return fail(Collections.emptyList(), msg, status);
    }

    /**
     * <p> ErrorCode를 가진 실패 응답을 반환한다. </p>
     * <pre>
     *     {
     *         "state" : HttpStatus Code,
     *         "result" : fail,
     *         "message" : message,
     *         "data" : [],
     *         "error" : [{error1}, {error2}...]
     *     }
     * </pre>
     *
     * @param errorCode 응답 바디 message 필드에 포함될 정보
     * @param status 응답 바디 status 필드에 포함될 응답 상태 코드
     * @return 응답 객체
     */
    public    ResponseEntity<?> fail(Object data, ErrorCode errorCode, HttpStatus status) {
        Body body = Body.builder()
                .state(status.value())
                .data(data)
                .result("fail")
                .error(Collections.emptyList())
                .errorCode(errorCode)
                .build();
        return ResponseEntity.ok(body);
    }
    public   ResponseEntity<?> fail(ErrorCode errorCode, HttpStatus status) {
        return fail(Collections.emptyList(), errorCode, status);
    }

    /**
     * <p> invalidFields 에러 처러. </p>
     * <pre>
     *     {
     *         "state" : HttpStatus Code,
     *         "result" : fail,
     *         "message" : message,
     *         "data" : [],
     *         "error" : [{error1}, {error2}...]
     *     }
     * </pre>
     *
     * @param errors 요청 내용에 대한 에러
     * @return 응답 객체
     */

    public   ResponseEntity<?> invalidFields(LinkedList<LinkedHashMap<String, String>> errors) {
        Body body = Body.builder()
                .state(HttpStatus.BAD_REQUEST.value())
                .data(Collections.emptyList())
                .result("fail")
                .message("")
                .error(errors)
                .build();
        return ResponseEntity.ok(body);
    }
}