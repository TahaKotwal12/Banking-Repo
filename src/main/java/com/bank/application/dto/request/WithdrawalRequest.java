package com.bank.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Withdrawal request DTO
 * EXACT same fields as legacy withdrawal action
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalRequest {

    /**
     * Client ID (bank_client_id)
     */
    @NotBlank(message = "Client ID is required")
    private String clientId;

    /**
     * Transaction details/description
     */
    @NotBlank(message = "Transaction details are required")
    private String details;

    /**
     * Withdrawal amount
     */
    @NotBlank(message = "Amount is required")
    @Pattern(regexp = "^[0-9]+$", message = "Amount must be a valid number")
    private String amount;
}
