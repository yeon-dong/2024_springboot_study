package com.hyundaiautoever.beexample.infrastructure.enums;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    SUCCESS("S", "success")
    ;

    private String returnCode;
    private String returnMessage;

    ResponseMessage(String returnCode, String returnMessage) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }
}
