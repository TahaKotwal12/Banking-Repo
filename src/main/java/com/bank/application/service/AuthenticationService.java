package com.bank.application.service;

import com.bank.application.dto.request.LoginRequest;
import com.bank.application.dto.response.LoginResponse;

/**
 * Authentication Service Interface
 * Handles login operations for all user types
 */
public interface AuthenticationService {

    /**
     * Check login credentials - exact same logic as legacy checkLogin()
     * 
     * @param request Login request with userName, password, bank_id, userType
     * @return LoginResponse with user session data
     * @throws Exception if login fails
     */
    LoginResponse checkLogin(LoginRequest request) throws Exception;
}
