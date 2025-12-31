package com.bank.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Add bank branch request DTO
 * EXACT same fields as legacy Bank_Detail entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddBankBranchRequest {

    @NotBlank(message = "Bank ID is required")
    private String bankId;

    @NotBlank(message = "Branch name is required")
    private String bankBranchName;

    @NotBlank(message = "Branch address is required")
    private String bankBranchAddress;

    @NotBlank(message = "Branch city is required")
    private String bankBranchCity;

    @NotBlank(message = "Branch phone is required")
    private String bankBranchPhone;
}
