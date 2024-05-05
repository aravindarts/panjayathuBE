package com.breaksloop.panjayathu.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LMResponse {

    private int responseCode;
    private Object data;
    private String message;

    public static LMResponse success(Object data,int responseCode) {
        return LMResponse.builder()
                .message("Success")
                .data(data)
                .responseCode(responseCode)
                .build();
    }
    public static LMResponse failed(Object data,int responseCode) {
        return LMResponse.builder()
                .message("Failed")
                .data(data)
                .responseCode(responseCode)
                .build();
    }
}
