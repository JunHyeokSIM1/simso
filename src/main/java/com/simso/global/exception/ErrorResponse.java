package com.simso.global.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private LocalDateTime now;
    private int status;
    private List<FieldError> errors;
    private String code;

    public ErrorResponse(LocalDateTime now) {
        this.now = now;
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
    private static class FieldError {
        private final String field;
        private final String value;
        private final String reason;
    }
}
