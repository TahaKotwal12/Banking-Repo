package com.bank.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Transaction response DTO
 * EXACT same fields as legacy Emp_AddTrans
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private String clientId;
    private String details;
    private String balance;
    private String deposit;
    private String withdrawn;
    private Date created;
}
