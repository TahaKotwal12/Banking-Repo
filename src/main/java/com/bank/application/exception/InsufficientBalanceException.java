package com.bank.application.exception;

/**
 * Exception thrown when account has insufficient balance for withdrawal
 */
public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(String clientId, double balance, double requestedAmount) {
        super(String.format("Insufficient balance for client %s. Available: %.2f, Requested: %.2f",
                clientId, balance, requestedAmount));
    }
}
