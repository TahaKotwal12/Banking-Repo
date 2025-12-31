package com.bank.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Client transactions response DTO
 * EXACT same structure as legacy viewcls action response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientTransactionsDTO {

    /**
     * Client ID
     */
    private String clientId;

    /**
     * Current balance (from last transaction)
     */
    private String currentBalance;

    /**
     * List of all transactions for this client
     */
    private List<TransactionDTO> transactions;
}
