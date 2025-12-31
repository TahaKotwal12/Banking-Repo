# Database Dummy Data - Quick Reference

## 📊 Test Data Summary

### Bank Branches (5)
- Main Branch - Mumbai
- Andheri Branch - Mumbai  
- Pune Branch - Pune
- Bangalore Branch - Bangalore
- Delhi Branch - Delhi

### Admin Accounts (3)
| Username | Password | Bank ID |
|----------|----------|---------|
| admin | admin | 1234 |
| superadmin | super123 | 5678 |
| manager | manager123 | 9012 |

### Employee Accounts (5)
| Username | Password | Bank ID | Name | Role | Branch |
|----------|----------|---------|------|------|--------|
| emp1234 | password | 1234 | Rajesh Sharma | Manager | Main Branch |
| emp2345 | password | 2345 | Priya Patel | Cashier | Andheri Branch |
| emp3456 | password | 3456 | Amit Desai | Manager | Pune Branch |
| emp4567 | password | 4567 | Sneha Reddy | Clerk | Bangalore Branch |
| emp5678 | password | 5678 | Vikram Chauhan | Asst Manager | Delhi Branch |

### Client Accounts (8)
| Username | Password | Bank ID | Name | Branch | Current Balance |
|----------|----------|---------|------|--------|----------------|
| rahul1001 | password | 1001 | Rahul Verma | Main Branch | ₹53,000 |
| anjali1002 | password | 1002 | Anjali Nair | Main Branch | ₹35,000 |
| suresh1003 | password | 1003 | Suresh Iyer | Andheri Branch | ₹58,000 |
| neha1004 | password | 1004 | Neha Kulkarni | Pune Branch | ₹80,000 |
| karthik1005 | password | 1005 | Karthik Rao | Bangalore Branch | ₹17,000 |
| pooja1006 | password | 1006 | Pooja Gupta | Delhi Branch | ₹38,000 |
| arjun1007 | password | 1007 | Arjun Menon | Main Branch | ₹95,000 |
| divya1008 | password | 1008 | Divya Shah | Pune Branch | ₹33,000 |

---

## 🚀 How to Run

### Option 1: Using psql Command Line
```bash
psql "postgresql://neondb_owner:npg_U1lwHoLqcpe0@ep-aged-recipe-ahjk7hrg-pooler.c-3.us-east-1.aws.neon.tech/banking_poc?sslmode=require" -f dummy_data.sql
```

### Option 2: Using pgAdmin or DBeaver
1. Connect to your Neon database
2. Open SQL editor
3. Copy and paste the contents of `dummy_data.sql`
4. Execute the script

### Option 3: Using Neon Console
1. Go to https://console.neon.tech
2. Select your project
3. Go to SQL Editor
4. Paste the script and run

---

## 🧪 Testing the API

### Test Admin Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "admin",
    "password": "admin",
    "bank_id": "1234",
    "userType": "admin"
  }'
```

### Test Employee Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "emp1234",
    "password": "password",
    "bank_id": "1234",
    "userType": "employee"
  }'
```

### Test Client Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "rahul1001",
    "password": "password",
    "bank_id": "1001",
    "userType": "client"
  }'
```

### Test Bank Details
```bash
curl -X GET http://localhost:8080/api/bank-details
```

---

## 📝 Notes

- **Passwords**: Employee and client passwords are MD5 hashed as "password"
- **Admin passwords**: Stored in plain text (matching legacy system)
- **Minimum Balance**: All clients have > ₹1,500 (minimum requirement)
- **Transaction History**: Each client has 2-4 transactions
- **Login History**: Multiple login records for testing last login feature

---

## ⚠️ Important

This is **DUMMY DATA** for testing only. Do not use in production!
