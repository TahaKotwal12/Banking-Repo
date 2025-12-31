package com.bank.application.controller;

import com.bank.application.dto.request.AddClientRequest;
import com.bank.application.dto.response.ApiResponse;
import com.bank.application.dto.response.ClientDetailsDTO;
import com.bank.application.service.ClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Client Controller
 * EXACT same functionality as legacy Emp_AddClient_Action
 */
@Slf4j
@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**
     * Add new client - Replaces legacy "addClient" and "addClients" actions
     * 
     * Legacy actions:
     * - addClient -> Emp_AddClient_Action.addClient() - saves client details
     * - addClients -> Emp_AddClient_Action.addClients() - saves login credentials
     * 
     * Modern endpoint: POST /api/clients
     * Modern result: Returns JSON with client details
     * 
     * Note: This combines both legacy actions into one endpoint
     * 
     * @param request Add client request with details and credentials
     * @return ApiResponse with client details
     */
    @PostMapping
    public ResponseEntity<ApiResponse<ClientDetailsDTO>> addClient(
            @Valid @RequestBody AddClientRequest request) {

        log.info("POST /api/clients - Adding new client: {}", request.getClientId());

        try {
            // Add client - EXACT same logic as legacy (details + login)
            ClientDetailsDTO client = clientService.addClient(request);

            // Success - return 201 CREATED
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponse.success("Client added successfully", client, 201));

        } catch (Exception e) {
            // Error - return 500 with error message
            log.error("Error adding client: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(
                    ApiResponse.error("Failed to add client: " + e.getMessage(), 500));
        }
    }
}
