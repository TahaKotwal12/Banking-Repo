package com.bank.application.exception;

import com.bank.application.dto.response.ApiResponse;
import com.bank.application.dto.response.ErrorDetail;
import com.bank.application.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Global exception handler for all REST controllers
 * Catches exceptions and returns standardized error responses
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

        /**
         * Handle validation errors (from @Valid annotation)
         */
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<Object>> handleValidationException(
                        MethodArgumentNotValidException ex,
                        HttpServletRequest request) {

                log.error("Validation error: {}", ex.getMessage());

                List<ErrorDetail> errors = new ArrayList<>();
                ex.getBindingResult().getAllErrors().forEach(error -> {
                        String fieldName = ((FieldError) error).getField();
                        String errorMessage = error.getDefaultMessage();
                        Object rejectedValue = ((FieldError) error).getRejectedValue();
                        errors.add(ErrorDetail.of(fieldName, errorMessage, rejectedValue));
                });

                ApiResponse<Object> response = ApiResponse.error(
                                Constants.Messages.INVALID_INPUT,
                                errors,
                                HttpStatus.BAD_REQUEST.value());
                response.setPath(request.getRequestURI());

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        /**
         * Handle authentication errors (invalid credentials)
         */
        @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity<ApiResponse<Object>> handleBadCredentialsException(
                        BadCredentialsException ex,
                        HttpServletRequest request) {

                log.error("Authentication error: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                Constants.Messages.LOGIN_FAILED,
                                HttpStatus.UNAUTHORIZED.value(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        /**
         * Handle access denied errors (insufficient permissions)
         */
        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(
                        AccessDeniedException ex,
                        HttpServletRequest request) {

                log.error("Access denied: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                Constants.Messages.FORBIDDEN,
                                HttpStatus.FORBIDDEN.value(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        /**
         * Handle resource not found exceptions
         */
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(
                        ResourceNotFoundException ex,
                        HttpServletRequest request) {

                log.error("Resource not found: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                ex.getMessage(),
                                HttpStatus.NOT_FOUND.value(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        /**
         * Handle duplicate resource exceptions
         */
        @ExceptionHandler(DuplicateResourceException.class)
        public ResponseEntity<ApiResponse<Object>> handleDuplicateResourceException(
                        DuplicateResourceException ex,
                        HttpServletRequest request) {

                log.error("Duplicate resource: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                ex.getMessage(),
                                HttpStatus.CONFLICT.value(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        /**
         * Handle invalid credentials exceptions
         */
        @ExceptionHandler(InvalidCredentialsException.class)
        public ResponseEntity<ApiResponse<Object>> handleInvalidCredentialsException(
                        InvalidCredentialsException ex,
                        HttpServletRequest request) {

                log.error("Invalid credentials: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                ex.getMessage(),
                                HttpStatus.UNAUTHORIZED.value(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        /**
         * Handle insufficient balance exceptions
         */
        @ExceptionHandler(InsufficientBalanceException.class)
        public ResponseEntity<ApiResponse<Object>> handleInsufficientBalanceException(
                        InsufficientBalanceException ex,
                        HttpServletRequest request) {

                log.error("Insufficient balance: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                ex.getMessage(),
                                HttpStatus.BAD_REQUEST.value(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        /**
         * Handle invalid token exceptions
         */
        @ExceptionHandler(InvalidTokenException.class)
        public ResponseEntity<ApiResponse<Object>> handleInvalidTokenException(
                        InvalidTokenException ex,
                        HttpServletRequest request) {

                log.error("Invalid token: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                ex.getMessage(),
                                HttpStatus.UNAUTHORIZED.value(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        /**
         * Handle all other exceptions
         */
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<Object>> handleGlobalException(
                        Exception ex,
                        HttpServletRequest request) {

                log.error("Unexpected error: ", ex);

                ApiResponse<Object> response = ApiResponse.error(
                                Constants.Messages.INTERNAL_ERROR,
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
}
