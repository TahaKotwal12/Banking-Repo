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

    /**
     * Get client by ID - Replaces legacy "editclient" action
     * 
     * Legacy action: editclient -> Emp_AddClient_Action.editclient()
     * Legacy result: Returns client details for editing
     * 
     * Modern endpoint: GET /api/clients/{clientId}
     * Modern result: Returns JSON with client details
     * 
     * @param clientId Client ID
     * @return ApiResponse with client details
     */
    @GetMapping("/{clientId}")
    public ResponseEntity<ApiResponse<ClientDetailsDTO>> getClient(
            @PathVariable String clientId) {

        log.info("GET /api/clients/{} - Fetching client details", clientId);

        try {
            // Get client - EXACT same logic as legacy
            ClientDetailsDTO client = clientService.getClientById(clientId);

            // Success - return 200 OK
            return ResponseEntity.ok(
                    ApiResponse.success("Client retrieved successfully", client));

        } catch (Exception e) {
            // Error - return 404 or 500
            log.error("Error fetching client: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error("Client not found: " + e.getMessage(), 404));
        }
    }

    /**
     * Update client details - Replaces legacy "eclient" action
     * 
     * Legacy action: eclient -> Emp_AddClient_Action.eclient()
     * Legacy message: "Changed Successfully"
     * Legacy result: Updates client details
     * 
     * Modern endpoint: PUT /api/clients/{clientId}
     * Modern result: Returns JSON with updated client details
     * 
     * @param clientId Client ID
     * @param request  Updated client details
     * @return ApiResponse with updated client details
     */
    @PutMapping("/{clientId}")
    public ResponseEntity<ApiResponse<ClientDetailsDTO>> updateClient(
            @PathVariable String clientId,
            @Valid @RequestBody AddClientRequest request) {

        log.info("PUT /api/clients/{} - Updating client details", clientId);

        try {
            // Update client - EXACT same logic as legacy
            ClientDetailsDTO client = clientService.updateClient(clientId, request);

            // Success - return 200 OK with EXACT same message as legacy
            return ResponseEntity.ok(
                    ApiResponse.success("Changed Successfully", client));

        } catch (Exception e) {
            // Error - return 404 or 500
            log.error("Error updating client: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.error("Failed to update client: " + e.getMessage(), 404));
        }
    }
}
