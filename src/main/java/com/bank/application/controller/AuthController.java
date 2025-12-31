package com.bank.application.controller;

import com.bank.application.dto.request.ChangePasswordRequest;
import com.bank.application.dto.response.ApiResponse;
import com.bank.application.service.PasswordService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication Controller
 * Handles login/logout/password operations
 * EXACT same functionality as legacy Admin_Login_Action, Emp_Login_Action,
 * Client_Login_Action
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private PasswordService passwordService;

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

    /**
     * Change password - Replaces legacy "changepw", "empchangepw", "clientchangepw"
     * actions
     * 
     * Legacy actions:
     * - changepw -> Admin_Login_Action.changepw() - admin change own password
     * - empchangepw -> Emp_Login_Action.changepw() - employee change own password
     * - clientchangepw -> Client_Login_Action.changepw() - client change own
     * password
     * 
     * Legacy validations:
     * - All fields required: "Please Enter All Values"
     * - New password must match confirm: "Password not matching"
     * - Old password must be correct: "Old Password not matching "
     * 
     * Legacy success message: "Password changed Successfully. Account will be
     * Logout"
     * 
     * Modern endpoint: PUT /api/auth/change-password
     * Modern result: Returns JSON with success message
     * 
     * @param request Change password request
     * @return ApiResponse with success message
     */
    @PutMapping("/change-password")
    public ResponseEntity<ApiResponse<String>> changePassword(
            @Valid @RequestBody ChangePasswordRequest request) {

        log.info("PUT /api/auth/change-password - User: {} ({})",
                request.getUserId(), request.getUserType());

        try {
            // Change password - EXACT same logic as legacy
            String message = passwordService.changePassword(request);

            // Success - return 200 OK with EXACT same message as legacy
            return ResponseEntity.ok(
                    ApiResponse.success(message, null));

        } catch (RuntimeException e) {
            // Error - return 400 with EXACT same error messages as legacy
            log.error("Password change failed: {}", e.getMessage());
            return ResponseEntity.badRequest().body(
                    ApiResponse.error(e.getMessage(), 400));
        }
    }
}
