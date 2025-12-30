package com.bank.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Login request DTO - matches exact legacy input format
 * Used for Admin, Employee, and Client login
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    /**
     * Username (bank_admin_user, bank_emp_user, or bank_client_user)
     */
    @NotBlank(message = "Please enter all values")
    private String userName;

    /**
     * Password (plain text - will be compared with MD5 hash in DB)
     */
    @NotBlank(message = "Please enter all values")
    private String password;

    /**
     * Bank ID (bank_admin_id, bank_emp_id, or bank_client_id)
     */
    @NotBlank(message = "Please enter all values")
    private String bank_id;

    /**
     * User type: "admin", "emp", or "client"
     * Used to determine which table to query
     */
    @NotBlank(message = "Please enter all values")
    private String userType;
}
