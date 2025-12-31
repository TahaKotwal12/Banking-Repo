package com.bank.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bank Details response DTO
 * EXACT same fields as legacy Bank_Detail entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankDetailsDTO {

    /**
     * Primary key
     */
    private Long id;

    /**
     * Bank ID
     */
    private Long bankId;

    /**
     * Branch name
     */
    private String bankBranchName;

    /**
     * Branch address
     */
    private String bankBranchAddress;

    /**
     * Branch city
     */
    private String bankBranchCity;

    /**
     * Branch phone number
     */
    private String bankBranchPhone;
}
