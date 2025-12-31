package com.bank.application.controller;

import com.bank.application.dto.request.DepositRequest;
import com.bank.application.dto.request.WithdrawalRequest;
import com.bank.application.dto.response.ApiResponse;
import com.bank.application.dto.response.ClientTransactionsDTO;
import com.bank.application.dto.response.TransactionDTO;
import com.bank.application.exception.InsufficientBalanceException;
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

    /**
     * Withdraw money - Replaces legacy "withdrawn" action
     * 
     * Legacy action: withdrawn -> Emp_AddTrans_Action.withdrawn()
     * Legacy success message: "Withdrwan Successfully " (note the typo - keeping it
     * exact)
     * Legacy error messages: "Insufficient Amount", "Client Amount Is: X", "Minimum
     * Amount Require 1500 Rs. "
     * Legacy result: Returns SUCCESS to addwith.jsp or ERROR
     * 
     * Modern endpoint: POST /api/transactions/withdraw
     * Modern result: Returns JSON with transaction details or error
     * 
     * @param request Withdrawal request with clientId, details, amount
     * @return ApiResponse with transaction details or error
     */
    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<TransactionDTO>> withdraw(
            @Valid @RequestBody WithdrawalRequest request) {

        log.info("POST /api/transactions/withdraw - Client: {}, Amount: {}",
                request.getClientId(), request.getAmount());

        try {
            // Process withdrawal - EXACT same logic as legacy
            TransactionDTO transaction = transactionService.processWithdrawal(request);

            // Success - return 200 OK with EXACT same message as legacy (including typo)
            return ResponseEntity.ok(
                    ApiResponse.success("Withdrwan Successfully ", transaction));

        } catch (InsufficientBalanceException e) {
            // Insufficient balance - return 400 with EXACT same error message as legacy
            log.warn("Insufficient balance for withdrawal: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error(e.getMessage(), 400));

        } catch (Exception e) {
            // Other errors - return 500
            log.error("Error processing withdrawal: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(
                    ApiResponse.error("Failed to process withdrawal: " + e.getMessage(), 500));
        }
    }

    /**
     * View client transactions - Replaces legacy "viewcls" action
     * 
     * Legacy action: viewcls -> Emp_AddTrans_Action.viewcls()
     * Legacy result: Returns list of transactions + current balance to view.jsp
     * 
     * Modern endpoint: GET /api/transactions/client/{clientId}
     * Modern result: Returns JSON with transaction list and current balance
     * 
     * @param clientId Client ID
     * @return ApiResponse with transactions and current balance
     */
    @GetMapping("/client/{clientId}")
    public ResponseEntity<ApiResponse<ClientTransactionsDTO>> viewClientTransactions(
            @PathVariable String clientId) {

        log.info("GET /api/transactions/client/{} - Fetching transactions", clientId);

        try {
            // Get transactions - EXACT same logic as legacy
            ClientTransactionsDTO transactions = transactionService.getClientTransactions(clientId);

            // Success - return 200 OK with transaction data
            return ResponseEntity.ok(
                    ApiResponse.success("Transactions retrieved successfully", transactions));

        } catch (Exception e) {
            // Error - return 500 with error message
            log.error("Error fetching transactions: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(
                    ApiResponse.error("Failed to retrieve transactions: " + e.getMessage(), 500));
        }
    }

    /**
     * View own transactions (Client self-service) - Replaces legacy "viewcl" action
     * 
     * Legacy action: viewcl -> Client_View_Action.viewcl()
     * Legacy logic: Gets client ID from session (user1), then calls same list() and
     * vish() methods
     * Legacy result: Returns list of transactions + current balance to view.jsp
     * 
     * Modern endpoint: GET /api/transactions/my-transactions/{clientId}
     * Modern result: Returns JSON with transaction list and current balance
     * 
     * Note: This uses the EXACT same service method as employee view
     * (getClientTransactions)
     * The only difference is the endpoint path to distinguish client vs employee
     * access
     * 
     * @param clientId Client ID (from authenticated session in production)
     * @return ApiResponse with transactions and current balance
     */
    @GetMapping("/my-transactions/{clientId}")
    public ResponseEntity<ApiResponse<ClientTransactionsDTO>> viewMyTransactions(
            @PathVariable String clientId) {

        log.info("GET /api/transactions/my-transactions/{} - Client viewing own transactions", clientId);

        try {
            // Reuse same service method - EXACT same logic as legacy
            ClientTransactionsDTO transactions = transactionService.getClientTransactions(clientId);

            // Success - return 200 OK with transaction data
            return ResponseEntity.ok(
                    ApiResponse.success("Transactions retrieved successfully", transactions));

        } catch (Exception e) {
            // Error - return 500 with error message
            log.error("Error fetching client transactions: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(
                    ApiResponse.error("Failed to retrieve transactions: " + e.getMessage(), 500));
        }
    }
}
