package com.bank.application.service.impl;

import com.bank.application.dto.request.AddClientRequest;
import com.bank.application.dto.response.ClientDetailsDTO;
import com.bank.application.entity.BankClientDetails;
import com.bank.application.entity.BankClientLogin;
import com.bank.application.repository.BankClientDetailsRepository;
import com.bank.application.repository.BankClientLoginRepository;
import com.bank.application.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Client Service Implementation
 * EXACT same logic as legacy Emp_AddClient_DaoImpl
 */
@Slf4j
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private BankClientDetailsRepository clientDetailsRepository;

    @Autowired
    private BankClientLoginRepository clientLoginRepository;

    /**
     * Add new client - EXACT same logic as legacy addClient() + addClients()
     * 
     * Legacy logic:
     * 1. addClient() - saves client details to bank_client_details
     * 2. addClients() - saves login credentials to bank_client_login with MD5
     * hashed password
     */
    @Override
    public ClientDetailsDTO addClient(AddClientRequest request) {

        log.debug("Adding new client: {}", request.getClientId());

        // Step 1: Save client details - EXACT same as legacy addClient()
        BankClientDetails clientDetails = BankClientDetails.builder()
                .bankClientId(request.getClientId())
                .branchName(request.getBranchName())
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .dob(request.getDob())
                .landLine(request.getLandLine())
                .mobile(request.getMobile())
                .email(request.getEmail())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .build();

        BankClientDetails savedDetails = clientDetailsRepository.save(clientDetails);

        // Step 2: Save login credentials - EXACT same as legacy addClients()
        // Hash password with MD5 - EXACT same as legacy md5() method
        String hashedPassword = md5(request.getPassword());

        BankClientLogin clientLogin = BankClientLogin.builder()
                .userName(request.getUsername())
                .password(hashedPassword)
                .bank_id(request.getClientId())
                .build();

        clientLoginRepository.save(clientLogin);

        log.info("Client added successfully: {}", request.getClientId());

        // Convert to DTO and return
        return convertToDTO(savedDetails);
    }

    /**
     * MD5 hash function - EXACT same as legacy md5() method in HibernateUtil
     */
    private String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            // Pad with leading zeros if needed
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    /**
     * Convert entity to DTO
     */
    private ClientDetailsDTO convertToDTO(BankClientDetails entity) {
        return ClientDetailsDTO.builder()
                .id(entity.getId())
                .clientId(entity.getBankClientId())
                .branchName(entity.getBranchName())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .lastName(entity.getLastName())
                .gender(entity.getGender())
                .dob(entity.getDob())
                .landLine(entity.getLandLine())
                .mobile(entity.getMobile())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .city(entity.getCity())
                .state(entity.getState())
                .build();
    }
}
