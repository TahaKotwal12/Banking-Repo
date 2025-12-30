package com.bank.application.controller;

import com.bank.application.dto.request.LoginRequest;
import com.bank.application.dto.response.ApiResponse;
import com.bank.application.dto.response.LoginResponse;
import com.bank.application.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication Controller
 * Handles login/logout operations
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService authenticationService;
    
    /**
     * Login endpoint - Replaces adminlogin, emplogin, and clientlogin actions
     * 
     * @param request LoginRequest with userName, password, bank_id, userType
     * @return ApiResponse with LoginResponse data or error message
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        
        try {
            // Call service - EXACT same logic as legacy checkLogin()
            LoginResponse response = authenticationService.checkLogin(request);
            
            // Success - return 200 OK with user data
            return ResponseEntity.ok(
                    ApiResponse.success("Login successful", response)
            );
            
        } catch (Exception e) {
            // Error - return 400 with EXACT same error message as legacy
            log.error("Login failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error(e.getMessage(), HttpStatus.BAD_REQUEST.value())
            );
        }
    }
}
