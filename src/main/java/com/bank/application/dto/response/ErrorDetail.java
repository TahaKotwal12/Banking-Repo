package com.bank.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a single validation error or field-level error
 * Used in error responses to provide detailed error information
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetail {

    /**
     * The field name that has the error (e.g., "username", "password")
     */
    private String field;

    /**
     * The error message for this field
     */
    private String message;

    /**
     * The rejected value (optional)
     */
    private Object rejectedValue;

    /**
     * Error code for programmatic handling (optional)
     */
    private String code;

    /**
     * Create a simple error detail with field and message
     */
    public static ErrorDetail of(String field, String message) {
        return ErrorDetail.builder()
                .field(field)
                .message(message)
                .build();
    }

    /**
     * Create an error detail with field, message, and rejected value
     */
    public static ErrorDetail of(String field, String message, Object rejectedValue) {
        return ErrorDetail.builder()
                .field(field)
                .message(message)
                .rejectedValue(rejectedValue)
                .build();
    }
}
