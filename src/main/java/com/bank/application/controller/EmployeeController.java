package com.bank.application.controller;

import com.bank.application.dto.request.AddEmployeeRequest;
import com.bank.application.dto.response.ApiResponse;
import com.bank.application.dto.response.EmployeeDetailsDTO;
import com.bank.application.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Employee Controller
 * EXACT same functionality as legacy Admin_AddEmp_Action
 */
@Slf4j
@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Add new employee - Replaces legacy "addEmp" and "addEmps" actions
     * 
     * Legacy actions:
     * - addEmp -> Admin_AddEmp_Action.addEmp() - saves employee details
     * - addEmps -> Admin_AddEmp_Action.addEmps() - saves login credentials
     * 
     * Legacy message: "Done"
     * 
     * Modern endpoint: POST /api/employees
     * Modern result: Returns JSON with employee details
     * 
     * Note: This combines both legacy actions into one endpoint
     * 
     * @param request Add employee request with details and credentials
     * @return ApiResponse with employee details
     */
    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDetailsDTO>> addEmployee(
            @Valid @RequestBody AddEmployeeRequest request) {

        log.info("POST /api/employees - Adding new employee: {}", request.getEmployeeId());

        try {
            // Add employee - EXACT same logic as legacy (details + login)
            EmployeeDetailsDTO employee = employeeService.addEmployee(request);

            // Success - return 201 CREATED with EXACT same message as legacy
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponse.success("Done", employee, 201));

        } catch (Exception e) {
            // Error - return 500 with error message
            log.error("Error adding employee: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(
                    ApiResponse.error("Failed to add employee: " + e.getMessage(), 500));
        }
    }
}
