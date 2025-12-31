package com.bank.application.controller;

import com.bank.application.dto.response.ApiResponse;
import com.bank.application.dto.response.BankDetailsDTO;
import com.bank.application.service.BankDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Bank Details Controller
 * EXACT same functionality as legacy Bank_Detail_Action
 */
@Slf4j
@RestController
@RequestMapping("/api/bank-details")
@CrossOrigin(origins = "*")
public class BankDetailsController {

    @Autowired
    private BankDetailsService bankDetailsService;

    /**
     * Get all bank details - Replaces legacy "detail" action
     * 
     * Legacy action: detail -> Bank_Detail_Action.execute()
     * Legacy query: "from Bank_Detail"
     * Legacy result: Returns list to bank_detail.jsp
     * 
     * Modern endpoint: GET /api/bank-details
     * Modern result: Returns JSON list wrapped in ApiResponse
     * 
     * @return ApiResponse with list of bank details
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<BankDetailsDTO>>> getAllBankDetails() {

        log.info("GET /api/bank-details - Fetching all bank details");

        try {
            // Call service - EXACT same logic as legacy execute() method
            List<BankDetailsDTO> bankDetails = bankDetailsService.getAllBankDetails();

            // Success - return 200 OK with bank details list
            return ResponseEntity.ok(
                    ApiResponse.success("Bank details retrieved successfully", bankDetails));

        } catch (Exception e) {
            // Error - return 500 with error message
            log.error("Error fetching bank details: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(
                    ApiResponse.error("Failed to retrieve bank details", 500));
        }
    }
}
