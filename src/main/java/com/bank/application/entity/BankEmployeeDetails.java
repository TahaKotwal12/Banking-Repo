package com.bank.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for bank_emp_details table
 * Stores employee personal information
 * EXACT same structure as legacy Admin_AddEmp entity
 */
@Entity
@Table(name = "bank_emp_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankEmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bank_emp_id", length = 255)
    private String bankEmpId;

    @Column(name = "role_name", length = 255)
    private String roleName;

    @Column(name = "branch_name", length = 255)
    private String branchName;

    @Column(name = "first_name", length = 255)
    private String firstName;

    @Column(name = "middle_name", length = 255)
    private String middleName;

    @Column(name = "last_name", length = 255)
    private String lastName;

    @Column(name = "gender", length = 255)
    private String gender;

    @Column(name = "dob_detail", length = 255)
    private String dob;

    @Column(name = "phone_land_no", length = 255)
    private String landLine;

    @Column(name = "phone_mobile_no", length = 255)
    private String mobile;

    @Column(name = "email_detail", length = 255)
    private String email;

    @Column(name = "add_detail", length = 255)
    private String address;

    @Column(name = "city_detail", length = 255)
    private String city;

    @Column(name = "state_detail", length = 255)
    private String state;
}
