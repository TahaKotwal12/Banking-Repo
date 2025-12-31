package com.bank.application.service.impl;

import com.bank.application.dto.request.AddEmployeeRequest;
import com.bank.application.dto.response.EmployeeDetailsDTO;
import com.bank.application.entity.BankEmployeeDetails;
import com.bank.application.entity.BankEmpLogin;
import com.bank.application.repository.BankEmployeeDetailsRepository;
import com.bank.application.repository.BankEmpLoginRepository;
import com.bank.application.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Employee Service Implementation
 * EXACT same logic as legacy Admin_AddEmp_DaoImpl
 */
@Slf4j
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private BankEmployeeDetailsRepository employeeDetailsRepository;

    @Autowired
    private BankEmpLoginRepository empLoginRepository;

    /**
     * Add new employee - EXACT same logic as legacy addEmp() + addEmps()
     * 
     * Legacy logic:
     * 1. addEmp() - saves employee details to bank_emp_details
     * 2. addEmps() - saves login credentials to bank_emp_login with MD5 hashed
     * password
     */
    @Override
    public EmployeeDetailsDTO addEmployee(AddEmployeeRequest request) {

        log.debug("Adding new employee: {}", request.getEmployeeId());

        // Step 1: Save employee details - EXACT same as legacy addEmp()
        BankEmployeeDetails employeeDetails = BankEmployeeDetails.builder()
                .bankEmpId(request.getEmployeeId())
                .roleName(request.getRoleName())
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

        BankEmployeeDetails savedDetails = employeeDetailsRepository.save(employeeDetails);

        // Step 2: Save login credentials - EXACT same as legacy addEmps()
        // Hash password with MD5 - EXACT same as legacy md5() method
        String hashedPassword = md5(request.getPassword());

        BankEmpLogin empLogin = BankEmpLogin.builder()
                .userName(request.getUsername())
                .password(hashedPassword)
                .bank_id(request.getEmployeeId())
                .build();

        empLoginRepository.save(empLogin);

        log.info("Employee added successfully: {}", request.getEmployeeId());

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
    private EmployeeDetailsDTO convertToDTO(BankEmployeeDetails entity) {
        return EmployeeDetailsDTO.builder()
                .id(entity.getId())
                .employeeId(entity.getBankEmpId())
                .roleName(entity.getRoleName())
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
