package com.bank.application.util;

/**
 * Application-wide constants
 */
public final class Constants {

    private Constants() {
        // Prevent instantiation
    }

    // ==================== API Response Messages ====================
    public static final class Messages {
        public static final String SUCCESS = "Operation completed successfully";
        public static final String CREATED = "Resource created successfully";
        public static final String UPDATED = "Resource updated successfully";
        public static final String DELETED = "Resource deleted successfully";
        public static final String NOT_FOUND = "Resource not found";
        public static final String ALREADY_EXISTS = "Resource already exists";
        public static final String INVALID_INPUT = "Invalid input provided";
        public static final String UNAUTHORIZED = "Unauthorized access";
        public static final String FORBIDDEN = "Access forbidden";
        public static final String INTERNAL_ERROR = "Internal server error occurred";

        // Authentication messages
        public static final String LOGIN_SUCCESS = "Login successful";
        public static final String LOGIN_FAILED = "Invalid credentials";
        public static final String LOGOUT_SUCCESS = "Logout successful";
        public static final String TOKEN_EXPIRED = "Token has expired";
        public static final String TOKEN_INVALID = "Invalid token";
        public static final String PASSWORD_CHANGED = "Password changed successfully";

        // Client messages
        public static final String CLIENT_CREATED = "Client account created successfully";
        public static final String CLIENT_UPDATED = "Client details updated successfully";
        public static final String CLIENT_NOT_FOUND = "Client not found";
        public static final String CLIENT_ALREADY_EXISTS = "Client ID already exists";

        // Employee messages
        public static final String EMPLOYEE_CREATED = "Employee created successfully";
        public static final String EMPLOYEE_UPDATED = "Employee details updated successfully";
        public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
        public static final String EMPLOYEE_ALREADY_EXISTS = "Employee ID already exists";

        // Transaction messages
        public static final String DEPOSIT_SUCCESS = "Deposit processed successfully";
        public static final String WITHDRAWAL_SUCCESS = "Withdrawal processed successfully";
        public static final String INSUFFICIENT_BALANCE = "Insufficient balance";
        public static final String TRANSACTION_NOT_FOUND = "Transaction not found";

        private Messages() {
        }
    }

    // ==================== User Roles ====================
    public static final class Roles {
        public static final String ADMIN = "ADMIN";
        public static final String EMPLOYEE = "EMPLOYEE";
        public static final String CLIENT = "CLIENT";

        private Roles() {
        }
    }

    // ==================== API Endpoints ====================
    public static final class Endpoints {
        public static final String API_BASE = "/api";
        public static final String AUTH = API_BASE + "/auth";
        public static final String CLIENTS = API_BASE + "/clients";
        public static final String EMPLOYEES = API_BASE + "/employees";
        public static final String TRANSACTIONS = API_BASE + "/transactions";
        public static final String BANK_DETAILS = API_BASE + "/bank-details";
        public static final String ADMIN = API_BASE + "/admin";

        private Endpoints() {
        }
    }

    // ==================== Validation Constants ====================
    public static final class Validation {
        public static final int USERNAME_MIN_LENGTH = 3;
        public static final int USERNAME_MAX_LENGTH = 50;
        public static final int PASSWORD_MIN_LENGTH = 8;
        public static final int PASSWORD_MAX_LENGTH = 100;
        public static final int NAME_MAX_LENGTH = 50;
        public static final int EMAIL_MAX_LENGTH = 100;
        public static final int PHONE_MAX_LENGTH = 15;
        public static final int ADDRESS_MAX_LENGTH = 255;

        private Validation() {
        }
    }

    // ==================== JWT Constants ====================
    public static final class Jwt {
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final String HEADER_STRING = "Authorization";
        public static final long ACCESS_TOKEN_EXPIRATION = 3600000; // 1 hour in milliseconds
        public static final long REFRESH_TOKEN_EXPIRATION = 86400000; // 24 hours in milliseconds

        private Jwt() {
        }
    }

    // ==================== Date/Time Formats ====================
    public static final class DateFormats {
        public static final String DATE_FORMAT = "yyyy-MM-dd";
        public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
        public static final String TIME_FORMAT = "HH:mm:ss";

        private DateFormats() {
        }
    }

    // ==================== Pagination ====================
    public static final class Pagination {
        public static final int DEFAULT_PAGE_SIZE = 20;
        public static final int MAX_PAGE_SIZE = 100;
        public static final String DEFAULT_SORT_FIELD = "id";
        public static final String DEFAULT_SORT_DIRECTION = "ASC";

        private Pagination() {
        }
    }
}
