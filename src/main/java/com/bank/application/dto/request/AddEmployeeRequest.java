package com.bank.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Add employee request DTO
 * EXACT same fields as legacy addEmp and addEmps actions
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddEmployeeRequest {

    // Employee Details (from Admin_AddEmp)
    @NotBlank(message = "Employee ID is required")
    private String employeeId;

    @NotBlank(message = "Role name is required")
    private String roleName;

    @NotBlank(message = "Branch name is required")
    private String branchName;

    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Date of birth is required")
    private String dob;

    private String landLine;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobile;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    // Employee Login Credentials (from Emp_Login)
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
