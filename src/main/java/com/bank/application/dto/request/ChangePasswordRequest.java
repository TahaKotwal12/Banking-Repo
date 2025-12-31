package com.bank.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Change password request DTO
 * EXACT same fields as legacy password change actions
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    /**
     * User ID (bank_id for admin/employee/client)
     */
    @NotBlank(message = "User ID is required")
    private String userId;

    /**
     * User type (admin, employee, client)
     */
    @NotBlank(message = "User type is required")
    private String userType;

    /**
     * Old password
     */
    @NotBlank(message = "Old password is required")
    private String oldPassword;

    /**
     * New password
     */
    @NotBlank(message = "New password is required")
    private String newPassword;

    /**
     * Confirm new password
     */
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;
}
