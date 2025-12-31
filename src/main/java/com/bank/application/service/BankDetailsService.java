package com.bank.application.service;

import com.bank.application.dto.request.AddBankBranchRequest;
import com.bank.application.dto.response.BankDetailsDTO;

import java.util.List;

/**
 * Bank Details Service Interface
 * EXACT same operations as legacy Bank_Detail_Action
 */
public interface BankDetailsService {

    /**
     * Get all bank details - exact same as legacy list() method
     * 
     * @return List of all bank branch details
     */
    List<BankDetailsDTO> getAllBankDetails();

    /**
     * Add new bank branch - exact same as legacy admin add bank action
     * 
     * @param request Add bank branch request
     * @return Created bank branch details
     */
    BankDetailsDTO addBankBranch(AddBankBranchRequest request);
}
