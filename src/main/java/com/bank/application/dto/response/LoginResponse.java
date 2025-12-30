package com.bank.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Login response DTO with clear, meaningful field names
 * Contains user information after successful login
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    /**
     * User role/type: "admin", "emp", or "client"
     */
    @JsonProperty("userType")
    private String userType;

    /**
     * Username
     */
    @JsonProperty("username")
    private String username;

    /**
     * Bank ID (admin_id, emp_id, or client_id)
     */
    @JsonProperty("bankId")
    private String bankId;

    /**
     * Last login timestamp
     */
    @JsonProperty("lastLogin")
    private String lastLogin;
}
