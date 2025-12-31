package com.bank.application.service.impl;

import com.bank.application.dto.request.ChangePasswordRequest;
import com.bank.application.entity.BankAdminLogin;
import com.bank.application.entity.BankClientLogin;
import com.bank.application.entity.BankEmpLogin;
import com.bank.application.repository.BankAdminLoginRepository;
import com.bank.application.repository.BankClientLoginRepository;
import com.bank.application.repository.BankEmpLoginRepository;
import com.bank.application.service.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Password Service Implementation
 * EXACT same logic as legacy password change DAO implementations
 */
@Slf4j
@Service
@Transactional
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    private BankAdminLoginRepository adminLoginRepository;

    @Autowired
    private BankEmpLoginRepository empLoginRepository;

    @Autowired
    private BankClientLoginRepository clientLoginRepository;

    /**
     * Change password - EXACT same logic as legacy changepw() methods
     * 
     * Legacy logic:
     * 1. Validate all fields are not empty
     * 2. Check if new password matches confirm password
     * 3. Verify old password is correct
     * 4. Update password if all validations pass
     * 5. Return success/error message
     */
    @Override
    public String changePassword(ChangePasswordRequest request) {

        log.debug("Changing password for user: {} ({})", request.getUserId(), request.getUserType());

        // Validation 1: Check if new password matches confirm password
        // Legacy: if (b.equals(c))
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Password not matching");
        }

        // Process based on user type
        String result;
        switch (request.getUserType().toLowerCase()) {
            case "admin":
                result = changeAdminPassword(request);
                break;
            case "employee":
                result = changeEmployeePassword(request);
                break;
            case "client":
                result = changeClientPassword(request);
                break;
            default:
                throw new RuntimeException("Invalid user type");
        }

        return result;
    }

    /**
     * Change admin password - EXACT same as legacy Admin_Login_DaoImpl.changepw()
     */
    private String changeAdminPassword(ChangePasswordRequest request) {

        // Find admin by ID and old password (plain text for admin)
        // Legacy: "SELECT chpw.password FROM Admin_Login chpw WHERE chpw.bank_id = :id
        // AND chpw.password = :oldpw"
        BankAdminLogin admin = adminLoginRepository.findByBank_idAndPassword(
                request.getUserId(),
                request.getOldPassword()).orElseThrow(() -> new RuntimeException("Old Password not matching "));

        // Update password (plain text for admin - matching legacy)
        admin.setPassword(request.getNewPassword());
        adminLoginRepository.save(admin);

        log.info("Admin password changed successfully: {}", request.getUserId());

        // EXACT same message as legacy
        return "Password changed Successfully. Account will be Logout";
    }

    /**
     * Change employee password - EXACT same as legacy Emp_Login_DaoImpl.changepw()
     */
    private String changeEmployeePassword(ChangePasswordRequest request) {

        // Hash old password with MD5 for comparison
        String hashedOldPassword = md5(request.getOldPassword());

        // Find employee by ID and old password (MD5 hashed)
        BankEmpLogin employee = empLoginRepository.findByBank_idAndPassword(
                request.getUserId(),
                hashedOldPassword).orElseThrow(() -> new RuntimeException("Old Password not matching "));

        // Update password with MD5 hash
        String hashedNewPassword = md5(request.getNewPassword());
        employee.setPassword(hashedNewPassword);
        empLoginRepository.save(employee);

        log.info("Employee password changed successfully: {}", request.getUserId());

        // EXACT same message as legacy
        return "Password changed Successfully. Account will be Logout";
    }

    /**
     * Change client password - EXACT same as legacy Client_Login_DaoImpl.changepw()
     */
    private String changeClientPassword(ChangePasswordRequest request) {

        // Hash old password with MD5 for comparison
        String hashedOldPassword = md5(request.getOldPassword());

        // Find client by ID and old password (MD5 hashed)
        BankClientLogin client = clientLoginRepository.findByBank_idAndPassword(
                request.getUserId(),
                hashedOldPassword).orElseThrow(() -> new RuntimeException("Old Password not matching "));

        // Update password with MD5 hash
        String hashedNewPassword = md5(request.getNewPassword());
        client.setPassword(hashedNewPassword);
        clientLoginRepository.save(client);

        log.info("Client password changed successfully: {}", request.getUserId());

        // EXACT same message as legacy
        return "Password changed Successfully. Account will be Logout";
    }

    /**
     * MD5 hash function - EXACT same as legacy md5() method
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
}
