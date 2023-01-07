package com.example.junit.web.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CMResponseDto<T> {
    private Integer code; // 1 성공, -1 실패
    private String msg; // 에러메세지, 성공에 대한 메세지
    private T body;

    @Builder
    public CMResponseDto(Integer code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }
}
