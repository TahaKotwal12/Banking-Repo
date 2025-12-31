-- ============================================
-- Banking Application - PostgreSQL Dummy Data
-- ============================================
-- This script creates sample data for testing the Spring Boot banking application
-- Compatible with PostgreSQL (Neon database)
-- Run this script after the application creates the tables

-- ============================================
-- 1. BANK DETAILS (Branch Information)
-- ============================================

INSERT INTO bank_details (bank_id, bank_branch_name, bank_branch_add, bank_branch_city, bank_branch_phone) VALUES
(1, 'Main Branch', '123 Main Street', 'Mumbai', '022-12345678'),
(2, 'Andheri Branch', '456 Andheri West', 'Mumbai', '022-23456789'),
(3, 'Pune Branch', '789 MG Road', 'Pune', '020-34567890'),
(4, 'Bangalore Branch', '321 Brigade Road', 'Bangalore', '080-45678901'),
(5, 'Delhi Branch', '654 Connaught Place', 'Delhi', '011-56789012');

-- ============================================
-- 2. ADMIN LOGIN
-- ============================================

INSERT INTO bank_admin_login (bank_admin_user, bank_admin_pass, bank_admin_id) VALUES
('admin', 'admin', '1234'),
('superadmin', 'super123', '5678'),
('manager', 'manager123', '9012');

-- ============================================
-- 3. ADMIN LOGIN HISTORY
-- ============================================

INSERT INTO bank_admin_loginman (bank_admin_id, created) VALUES
('1234', '2025-12-30 10:00:00'),
('1234', '2025-12-30 14:30:00'),
('1234', '2025-12-31 09:15:00'),
('5678', '2025-12-30 11:00:00'),
('5678', '2025-12-31 10:00:00'),
('9012', '2025-12-30 15:00:00');

-- ============================================
-- 4. EMPLOYEE DETAILS
-- ============================================

INSERT INTO bank_emp_details (bank_emp_id, role_name, branch_name, first_name, middle_name, last_name, gender, dob_detail, phone_land_no, phone_mobile_no, email_detail, add_detail, city_detail, state_detail) VALUES
('1234', 'Manager', 'Main Branch', 'Rajesh', 'Kumar', 'Sharma', 'Male', '1985-05-15', '022-11111111', '9876543210', 'rajesh.sharma@bank.com', '10/A, Andheri East', 'Mumbai', 'Maharashtra'),
('2345', 'Cashier', 'Andheri Branch', 'Priya', 'S', 'Patel', 'Female', '1990-08-20', '022-22222222', '9876543211', 'priya.patel@bank.com', '20/B, Bandra West', 'Mumbai', 'Maharashtra'),
('3456', 'Manager', 'Pune Branch', 'Amit', 'R', 'Desai', 'Male', '1988-03-10', '020-33333333', '9876543212', 'amit.desai@bank.com', '30/C, Koregaon Park', 'Pune', 'Maharashtra'),
('4567', 'Clerk', 'Bangalore Branch', 'Sneha', 'M', 'Reddy', 'Female', '1992-11-25', '080-44444444', '9876543213', 'sneha.reddy@bank.com', '40/D, Indiranagar', 'Bangalore', 'Karnataka'),
('5678', 'Assistant Manager', 'Delhi Branch', 'Vikram', 'Singh', 'Chauhan', 'Male', '1987-07-18', '011-55555555', '9876543214', 'vikram.chauhan@bank.com', '50/E, Karol Bagh', 'Delhi', 'Delhi');

-- ============================================
-- 5. EMPLOYEE LOGIN
-- ============================================
-- Note: Passwords are MD5 hashed
-- emp1234 = 5f4dcc3b5aa765d61d8327deb882cf99 (password)
-- emp2345 = 5f4dcc3b5aa765d61d8327deb882cf99 (password)

INSERT INTO bank_emp_login (bank_emp_user, bank_emp_pass, bank_emp_id) VALUES
('emp1234', '5f4dcc3b5aa765d61d8327deb882cf99', '1234'),
('emp2345', '5f4dcc3b5aa765d61d8327deb882cf99', '2345'),
('emp3456', '5f4dcc3b5aa765d61d8327deb882cf99', '3456'),
('emp4567', '5f4dcc3b5aa765d61d8327deb882cf99', '4567'),
('emp5678', '5f4dcc3b5aa765d61d8327deb882cf99', '5678');

-- ============================================
-- 6. EMPLOYEE LOGIN HISTORY
-- ============================================

INSERT INTO bank_emp_loginman (bank_emp_id, created) VALUES
('1234', '2025-12-30 09:00:00'),
('1234', '2025-12-30 13:00:00'),
('1234', '2025-12-31 08:30:00'),
('2345', '2025-12-30 09:30:00'),
('2345', '2025-12-31 09:00:00'),
('3456', '2025-12-30 10:00:00'),
('3456', '2025-12-31 09:30:00'),
('4567', '2025-12-30 10:30:00'),
('5678', '2025-12-30 11:00:00');

-- ============================================
-- 7. CLIENT DETAILS
-- ============================================

INSERT INTO bank_client_details (bank_client_id, branch_name, first_name, middle_name, last_name, gender, dob_detail, phone_land_no, phone_mobile_no, email_detail, add_detail, city_detail, state_detail) VALUES
('1001', 'Main Branch', 'Rahul', 'Kumar', 'Verma', 'Male', '1995-01-15', '022-66666666', '9988776655', 'rahul.verma@email.com', '101, Juhu', 'Mumbai', 'Maharashtra'),
('1002', 'Main Branch', 'Anjali', 'S', 'Nair', 'Female', '1993-06-20', '022-77777777', '9988776656', 'anjali.nair@email.com', '102, Powai', 'Mumbai', 'Maharashtra'),
('1003', 'Andheri Branch', 'Suresh', 'M', 'Iyer', 'Male', '1990-09-10', '022-88888888', '9988776657', 'suresh.iyer@email.com', '103, Andheri', 'Mumbai', 'Maharashtra'),
('1004', 'Pune Branch', 'Neha', 'R', 'Kulkarni', 'Female', '1994-12-05', '020-99999999', '9988776658', 'neha.kulkarni@email.com', '104, Shivaji Nagar', 'Pune', 'Maharashtra'),
('1005', 'Bangalore Branch', 'Karthik', 'P', 'Rao', 'Male', '1991-03-25', '080-11111111', '9988776659', 'karthik.rao@email.com', '105, Whitefield', 'Bangalore', 'Karnataka'),
('1006', 'Delhi Branch', 'Pooja', 'K', 'Gupta', 'Female', '1996-08-30', '011-22222222', '9988776660', 'pooja.gupta@email.com', '106, Rohini', 'Delhi', 'Delhi'),
('1007', 'Main Branch', 'Arjun', 'S', 'Menon', 'Male', '1989-11-12', '022-33333333', '9988776661', 'arjun.menon@email.com', '107, Bandra', 'Mumbai', 'Maharashtra'),
('1008', 'Pune Branch', 'Divya', 'A', 'Shah', 'Female', '1997-04-18', '020-44444444', '9988776662', 'divya.shah@email.com', '108, Kothrud', 'Pune', 'Maharashtra');

-- ============================================
-- 8. CLIENT LOGIN
-- ============================================
-- Note: Passwords are MD5 hashed
-- client123 = 5f4dcc3b5aa765d61d8327deb882cf99 (password)

INSERT INTO bank_client_login (bank_client_user, bank_client_pass, bank_client_id) VALUES
('rahul1001', '5f4dcc3b5aa765d61d8327deb882cf99', '1001'),
('anjali1002', '5f4dcc3b5aa765d61d8327deb882cf99', '1002'),
('suresh1003', '5f4dcc3b5aa765d61d8327deb882cf99', '1003'),
('neha1004', '5f4dcc3b5aa765d61d8327deb882cf99', '1004'),
('karthik1005', '5f4dcc3b5aa765d61d8327deb882cf99', '1005'),
('pooja1006', '5f4dcc3b5aa765d61d8327deb882cf99', '1006'),
('arjun1007', '5f4dcc3b5aa765d61d8327deb882cf99', '1007'),
('divya1008', '5f4dcc3b5aa765d61d8327deb882cf99', '1008');

-- ============================================
-- 9. CLIENT LOGIN HISTORY
-- ============================================

INSERT INTO bank_client_loginman (bank_client_id, created) VALUES
('1001', '2025-12-30 10:00:00'),
('1001', '2025-12-30 16:00:00'),
('1001', '2025-12-31 10:00:00'),
('1002', '2025-12-30 11:00:00'),
('1002', '2025-12-31 11:00:00'),
('1003', '2025-12-30 12:00:00'),
('1004', '2025-12-30 13:00:00'),
('1005', '2025-12-30 14:00:00'),
('1006', '2025-12-30 15:00:00'),
('1007', '2025-12-30 16:00:00'),
('1008', '2025-12-30 17:00:00');

-- ============================================
-- 10. TRANSACTIONS
-- ============================================

-- Client 1001 transactions
INSERT INTO bank_trans (bank_client_id, details, balance, deposit, withdrawn, created) VALUES
('1001', 'Initial Deposit', '10000', '10000', NULL, '2025-12-01 10:00:00'),
('1001', 'Cash Deposit', '15000', '5000', NULL, '2025-12-10 11:00:00'),
('1001', 'ATM Withdrawal', '13000', NULL, '2000', '2025-12-15 14:30:00'),
('1001', 'Salary Credit', '53000', '40000', NULL, '2025-12-25 09:00:00');

-- Client 1002 transactions
INSERT INTO bank_trans (bank_client_id, details, balance, deposit, withdrawn, created) VALUES
('1002', 'Initial Deposit', '25000', '25000', NULL, '2025-12-01 11:00:00'),
('1002', 'Cash Withdrawal', '20000', NULL, '5000', '2025-12-12 15:00:00'),
('1002', 'Cheque Deposit', '35000', '15000', NULL, '2025-12-20 10:30:00');

-- Client 1003 transactions
INSERT INTO bank_trans (bank_client_id, details, balance, deposit, withdrawn, created) VALUES
('1003', 'Initial Deposit', '50000', '50000', NULL, '2025-12-01 12:00:00'),
('1003', 'Cash Deposit', '60000', '10000', NULL, '2025-12-08 13:00:00'),
('1003', 'ATM Withdrawal', '58000', NULL, '2000', '2025-12-18 16:00:00');

-- Client 1004 transactions
INSERT INTO bank_trans (bank_client_id, details, balance, deposit, withdrawn, created) VALUES
('1004', 'Initial Deposit', '30000', '30000', NULL, '2025-12-02 10:00:00'),
('1004', 'Salary Credit', '80000', '50000', NULL, '2025-12-25 09:30:00');

-- Client 1005 transactions
INSERT INTO bank_trans (bank_client_id, details, balance, deposit, withdrawn, created) VALUES
('1005', 'Initial Deposit', '15000', '15000', NULL, '2025-12-03 11:00:00'),
('1005', 'Cash Deposit', '20000', '5000', NULL, '2025-12-15 12:00:00'),
('1005', 'Cash Withdrawal', '17000', NULL, '3000', '2025-12-28 14:00:00');

-- Client 1006 transactions
INSERT INTO bank_trans (bank_client_id, details, balance, deposit, withdrawn, created) VALUES
('1006', 'Initial Deposit', '40000', '40000', NULL, '2025-12-04 10:00:00'),
('1006', 'ATM Withdrawal', '38000', NULL, '2000', '2025-12-20 15:30:00');

-- Client 1007 transactions
INSERT INTO bank_trans (bank_client_id, details, balance, deposit, withdrawn, created) VALUES
('1007', 'Initial Deposit', '100000', '100000', NULL, '2025-12-05 09:00:00'),
('1007', 'Cash Withdrawal', '95000', NULL, '5000', '2025-12-22 11:00:00');

-- Client 1008 transactions
INSERT INTO bank_trans (bank_client_id, details, balance, deposit, withdrawn, created) VALUES
('1008', 'Initial Deposit', '20000', '20000', NULL, '2025-12-06 10:00:00'),
('1008', 'Cheque Deposit', '35000', '15000', NULL, '2025-12-16 13:00:00'),
('1008', 'ATM Withdrawal', '33000', NULL, '2000', '2025-12-26 16:00:00');

-- ============================================
-- SUMMARY
-- ============================================
-- Bank Branches: 5
-- Admins: 3 (admin/admin, superadmin/super123, manager/manager123)
-- Employees: 5 (all with password: password)
-- Clients: 8 (all with password: password)
-- Transactions: 25 total
-- ============================================

-- ============================================
-- TEST CREDENTIALS
-- ============================================
-- ADMIN LOGIN:
--   Username: admin, Password: admin, Bank ID: 1234
--
-- EMPLOYEE LOGIN:
--   Username: emp1234, Password: password, Bank ID: 1234
--
-- CLIENT LOGIN:
--   Username: rahul1001, Password: password, Bank ID: 1001
-- ============================================
