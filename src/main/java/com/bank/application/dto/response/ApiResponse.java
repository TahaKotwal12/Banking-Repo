package com.bank.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Generic API Response wrapper class
 * All API responses will be wrapped in this structure for consistency
 * 
 * @param <T> The type of data being returned
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    /**
     * Response status: "success" or "error"
     */
    private String status;

    /**
     * Human-readable message describing the response
     */
    private String message;

    /**
     * The actual data payload (null for error responses)
     */
    private T data;

    /**
     * List of validation errors (only for error responses)
     */
    private List<ErrorDetail> errors;

    /**
     * Timestamp when the response was generated
     */
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    /**
     * HTTP status code
     */
    private Integer statusCode;

    /**
     * Request path that generated this response
     */
    private String path;

    // ==================== Static Factory Methods ====================

    /**
     * Create a success response with data
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .status("success")
                .message(message)
                .data(data)
                .statusCode(200)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Create a success response without data
     */
    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .status("success")
                .message(message)
                .statusCode(200)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Create a success response with custom status code
     */
    public static <T> ApiResponse<T> success(String message, T data, Integer statusCode) {
        return ApiResponse.<T>builder()
                .status("success")
                .message(message)
                .data(data)
                .statusCode(statusCode)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Create an error response with message
     */
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .status("error")
                .message(message)
                .statusCode(400)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Create an error response with message and status code
     */
    public static <T> ApiResponse<T> error(String message, Integer statusCode) {
        return ApiResponse.<T>builder()
                .status("error")
                .message(message)
                .statusCode(statusCode)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Create an error response with validation errors
     */
    public static <T> ApiResponse<T> error(String message, List<ErrorDetail> errors) {
        return ApiResponse.<T>builder()
                .status("error")
                .message(message)
                .errors(errors)
                .statusCode(400)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Create an error response with validation errors and status code
     */
    public static <T> ApiResponse<T> error(String message, List<ErrorDetail> errors, Integer statusCode) {
        return ApiResponse.<T>builder()
                .status("error")
                .message(message)
                .errors(errors)
                .statusCode(statusCode)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Create an error response with path
     */
    public static <T> ApiResponse<T> error(String message, Integer statusCode, String path) {
        return ApiResponse.<T>builder()
                .status("error")
                .message(message)
                .statusCode(statusCode)
                .path(path)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
