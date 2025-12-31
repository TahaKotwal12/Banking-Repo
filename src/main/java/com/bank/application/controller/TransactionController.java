package com.bank.application.controller;

import com.bank.application.dto.request.DepositRequest;
import com.bank.application.dto.response.ApiResponse;
import com.bank.application.dto.response.TransactionDTO;
import com.bank.application.service.TransactionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Transaction Controller
 * EXACT same functionality as legacy Emp_AddTrans_Action
 */
@Slf4j
@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Deposit money - Replaces legacy "deposit" action
     * 
     * Legacy action: deposit -> Emp_AddTrans_Action.deposit()
     * Legacy message: "Added Amount Successfully"
     * Legacy result: Returns SUCCESS to addtrans.jsp
     * 
     * Modern endpoint: POST /api/transactions/deposit
     * Modern result: Returns JSON with transaction details
     * 
     * @param request Deposit request with clientId, details, amount
     * @return ApiResponse with transaction details
     */
    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse<TransactionDTO>> deposit(
            @Valid @RequestBody DepositRequest request) {

        log.info("POST /api/transactions/deposit - Client: {}, Amount: {}",
                request.getClientId(), request.getAmount());

        try {
            // Process deposit - EXACT same logic as legacy
            TransactionDTO transaction = transactionService.processDeposit(request);

            // Success - return 201 CREATED with EXACT same message as legacy
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponse.success("Added Amount Successfully", transaction, 201));

        } catch (Exception e) {
            // Error - return 500 with error message
            log.error("Error processing deposit: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(
                    ApiResponse.error("Failed to process deposit: " + e.getMessage(), 500));
        }
    }
}
