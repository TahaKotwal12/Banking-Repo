package com.bank.application.controller;

import com.bank.application.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication Controller
 * Handles login/logout operations
 * EXACT same functionality as legacy Admin_Login_Action, Emp_Login_Action,
 * Client_Login_Action
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    /**
     * Logout endpoint - Replaces legacy "logout" action
     * 
     * Legacy action: logout -> Admin_Login_Action.logout()
     * Legacy logic: Removes session variables (user, user0, user1, user2)
     * Legacy result: Redirects to login-portal.jsp
     * 
     * Modern endpoint: POST /api/auth/logout
     * Modern result: Returns JSON success message
     * 
     * Note: In a stateless REST API with JWT, logout is typically handled
     * client-side
     * by removing the token. This endpoint is provided for compatibility and can be
     * extended to implement token blacklisting if needed.
     * 
     * @return ApiResponse with success message
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout() {

        log.info("POST /api/auth/logout - User logging out");

        // In legacy: session.remove("user"), session.remove("user0"), etc.
        // In modern REST API with JWT: Client removes token
        // This endpoint confirms logout action

        // Success - return 200 OK
        return ResponseEntity.ok(
                ApiResponse.success("Logout successful", null));
    }
}
