package com.bank.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Client details response DTO
 * EXACT same fields as legacy Emp_AddClient
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDetailsDTO {

    private Long id;
    private String clientId;
    private String branchName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String dob;
    private String landLine;
    private String mobile;
    private String email;
    private String address;
    private String city;
    private String state;
}
