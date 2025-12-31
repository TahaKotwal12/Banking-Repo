package com.bank.application.service.impl;

import com.bank.application.dto.response.BankDetailsDTO;
import com.bank.application.entity.BankDetails;
import com.bank.application.repository.BankDetailsRepository;
import com.bank.application.service.BankDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Bank Details Service Implementation
 * EXACT same logic as legacy Bank_Detail_DaoImpl
 */
@Slf4j
@Service
@Transactional
public class BankDetailsServiceImpl implements BankDetailsService {

    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    /**
     * Get all bank details - EXACT same as legacy list() method
     * Legacy query: "from Bank_Detail"
     * Modern equivalent: findAll()
     */
    @Override
    public List<BankDetailsDTO> getAllBankDetails() {

        log.debug("Fetching all bank details");

        // Get all bank details from database - EXACT same as legacy
        List<BankDetails> bankDetailsList = bankDetailsRepository.findAll();

        // Convert entities to DTOs
        return bankDetailsList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convert entity to DTO - maintains exact same field mapping
     */
    private BankDetailsDTO convertToDTO(BankDetails entity) {
        return BankDetailsDTO.builder()
                .id(entity.getId())
                .bankId(entity.getBankId())
                .bankBranchName(entity.getBankBranchName())
                .bankBranchAddress(entity.getBankBranchAddress())
                .bankBranchCity(entity.getBankBranchCity())
                .bankBranchPhone(entity.getBankBranchPhone())
                .build();
    }
}
