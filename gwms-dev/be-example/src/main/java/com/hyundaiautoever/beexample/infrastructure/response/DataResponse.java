package com.hyundaiautoever.beexample.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<D> {
    private String returnCode;
    private String returnMessage;
    private D data;

    public DataResponse(String returnCode, String returnMessage, D data) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.data = data;
    }

    public static <D> DataResponse<D> of(String returnCode, String returnMessage) {
        return DataResponse.of(returnCode, returnMessage, null);
    }

    public static <D> DataResponse<D> of(String returnCode, String returnMessage, D data) {
        return new DataResponse<>(returnCode, returnMessage, data);
    }
}
