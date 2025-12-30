package com.bank.application.service.impl;

import com.bank.application.dto.request.LoginRequest;
import com.bank.application.dto.response.LoginResponse;
import com.bank.application.entity.*;
import com.bank.application.repository.*;
import com.bank.application.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Authentication Service Implementation
 * Replicates EXACT logic from legacy Struts actions
 */
@Slf4j
@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private BankAdminLoginRepository adminLoginRepository;

    @Autowired
    private BankAdminLoginmanRepository adminLoginmanRepository;

    @Autowired
    private BankEmpLoginRepository empLoginRepository;

    @Autowired
    private BankEmpLoginmanRepository empLoginmanRepository;

    @Autowired
    private BankClientLoginRepository clientLoginRepository;

    @Autowired
    private BankClientLoginmanRepository clientLoginmanRepository;

    /**
     * Check login - EXACT same logic as legacy checkLogin()
     * Validates credentials and saves login history
     */
    @Override
    public LoginResponse checkLogin(LoginRequest request) throws Exception {

        String userName = request.getUserName();
        String password = request.getPassword();
        String bank_id = request.getBank_id();
        String userType = request.getUserType();

        // Validate all fields are filled - EXACT same validation as legacy
        if (userName == null || userName.equals("") ||
                password == null || password.equals("") ||
                bank_id == null || bank_id.equals("")) {

            // Throw exception with EXACT same error message
            throw new Exception(getEmptyFieldsErrorMessage(userType));
        }

        // Route to appropriate login method based on user type
        switch (userType.toLowerCase()) {
            case "admin":
                return checkAdminLogin(userName, password, bank_id);
            case "emp":
            case "employee":
                return checkEmpLogin(userName, password, bank_id);
            case "client":
            case "user":
                return checkClientLogin(userName, password, bank_id);
            default:
                throw new Exception("Invalid user type");
        }
    }

    /**
     * Admin login - EXACT same logic as Admin_Login_Action.checkLogin()
     */
    private LoginResponse checkAdminLogin(String userName, String password, String bank_id) throws Exception {

        // Query database - EXACT same query as legacy
        var loginOpt = adminLoginRepository.findByUserNameAndPasswordAndBankId(userName, password, bank_id);

        if (loginOpt.isPresent()) {
            // Login successful - EXACT same logic as legacy

            // Save login history - EXACT same as legacy
            BankAdminLoginman loginHistory = BankAdminLoginman.builder()
                    .bank_id(bank_id)
                    .userName(userName)
                    .created(new Date())
                    .build();
            adminLoginmanRepository.save(loginHistory);

            // Get last login timestamp - EXACT same query as legacy
            String lastLoginTime = getLastLoginTime(adminLoginmanRepository.findLastLoginsByBankId(bank_id));

            // Return response with clear field names
            return LoginResponse.builder()
                    .userType("admin")
                    .username(userName)
                    .bankId(bank_id)
                    .lastLogin(lastLoginTime)
                    .build();

        } else {
            // Login failed - EXACT same error message as legacy
            throw new Exception("Invalid Admin id/password/ Admin Id");
        }
    }

    /**
     * Employee login - EXACT same logic as Emp_Login_Action.checkLogin()
     */
    private LoginResponse checkEmpLogin(String userName, String password, String bank_id) throws Exception {

        // Query database - EXACT same query as legacy
        var loginOpt = empLoginRepository.findByUserNameAndPasswordAndBankId(userName, password, bank_id);

        if (loginOpt.isPresent()) {
            // Login successful - EXACT same logic as legacy

            // Save login history - EXACT same as legacy
            BankEmpLoginman loginHistory = BankEmpLoginman.builder()
                    .bank_id(bank_id)
                    .userName(userName)
                    .created(new Date())
                    .build();
            empLoginmanRepository.save(loginHistory);

            // Get last login timestamp - EXACT same query as legacy
            String lastLoginTime = getLastLoginTime(empLoginmanRepository.findLastLoginsByBankId(bank_id));

            // Return response with clear field names
            return LoginResponse.builder()
                    .userType("emp")
                    .username(userName)
                    .bankId(bank_id)
                    .lastLogin(lastLoginTime)
                    .build();

        } else {
            // Login failed - EXACT same error message as legacy
            throw new Exception("Invalid user Id/Password/ Employee Id");
        }
    }

    /**
     * Client login - EXACT same logic as Client_Login_Action.checkLogin()
     */
    private LoginResponse checkClientLogin(String userName, String password, String bank_id) throws Exception {

        // Query database - EXACT same query as legacy
        var loginOpt = clientLoginRepository.findByUserNameAndPasswordAndBankId(userName, password, bank_id);

        if (loginOpt.isPresent()) {
            // Login successful - EXACT same logic as legacy

            // Save login history - EXACT same as legacy
            BankClientLoginman loginHistory = BankClientLoginman.builder()
                    .bank_id(bank_id)
                    .userName(userName)
                    .created(new Date())
                    .build();
            clientLoginmanRepository.save(loginHistory);

            // Get last login timestamp - EXACT same query as legacy
            String lastLoginTime = getLastLoginTime(clientLoginmanRepository.findLastLoginsByBankId(bank_id));

            // Return response with clear field names
            return LoginResponse.builder()
                    .userType("client")
                    .username(userName)
                    .bankId(bank_id)
                    .lastLogin(lastLoginTime)
                    .build();

        } else {
            // Login failed - EXACT same error message as legacy
            throw new Exception("Invalid user id/password/ Bank _Id");
        }
    }

    /**
     * Get last login time from history - EXACT same logic as legacy
     * Gets the second-to-last login (index 1) because current login is at index 0
     */
    private String getLastLoginTime(List<Date> loginHistory) {
        try {
            if (loginHistory != null && loginHistory.size() > 1) {
                // Get second item (index 1) - EXACT same as legacy: results.get(1)
                return loginHistory.get(1).toString();
            }
        } catch (Exception e) {
            log.error("Error getting last login time: {}", e.getMessage());
        }
        return null;
    }

    /**
     * Get error message for empty fields - EXACT same messages as legacy
     */
    private String getEmptyFieldsErrorMessage(String userType) {
        switch (userType.toLowerCase()) {
            case "admin":
                return "Please enter all values"; // Admin_Login_Action line 41
            case "emp":
            case "employee":
                return "Please Enter All Values"; // Emp_Login_Action line 38
            case "client":
            case "user":
                return "Please enter all values"; // Client_Login_Action line 37
            default:
                return "Please enter all values";
        }
    }
}
