package com.hyundaiautoever.beexample.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LabelResponse<L> {
    private String returnCode;
    private String returnMessage;
    private L label;

    private LabelResponse(String returnCode, String returnMessage, L label) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.label = label;
    }

    public static <L> LabelResponse<L> of(String returnCode, String returnMessage) {
        return LabelResponse.of(returnCode, returnMessage, null);
    }

    public static <L> LabelResponse<L> of(String returnCode, String returnMessage, L label) {
        return new LabelResponse<>(returnCode, returnMessage, label);
    }
}
