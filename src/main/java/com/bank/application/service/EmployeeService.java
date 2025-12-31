package com.bank.application.service;

import com.bank.application.dto.request.AddEmployeeRequest;
import com.bank.application.dto.response.EmployeeDetailsDTO;

/**
 * Employee Service Interface
 * EXACT same operations as legacy Admin_AddEmp_Action
 */
public interface EmployeeService {

    /**
     * Add new employee - exact same logic as legacy addEmp() + addEmps()
     * Creates both employee details and login credentials
     * 
     * @param request Add employee request with details and credentials
     * @return Employee details DTO
     */
    EmployeeDetailsDTO addEmployee(AddEmployeeRequest request);
}
