package com.bank.application.service;

import com.bank.application.dto.request.ChangePasswordRequest;

/**
 * Password Service Interface
 * EXACT same operations as legacy password change actions
 */
public interface PasswordService {

    /**
     * Change password for any user type - exact same logic as legacy changepw()
     * 
     * @param request Change password request
     * @return Success message
     */
    String changePassword(ChangePasswordRequest request);
}
