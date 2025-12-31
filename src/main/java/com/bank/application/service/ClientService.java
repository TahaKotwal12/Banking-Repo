package com.bank.application.service;

import com.bank.application.dto.request.AddClientRequest;
import com.bank.application.dto.response.ClientDetailsDTO;

/**
 * Client Service Interface
 * EXACT same operations as legacy Emp_AddClient_Action
 */
public interface ClientService {

    /**
     * Add new client - exact same logic as legacy addClient() + addClients()
     * Creates both client details and login credentials
     * 
     * @param request Add client request with details and credentials
     * @return Client details DTO
     */
    ClientDetailsDTO addClient(AddClientRequest request);

    /**
     * Get client by ID - exact same logic as legacy editclient()
     * 
     * @param clientId Client ID
     * @return Client details DTO
     */
    ClientDetailsDTO getClientById(String clientId);

    /**
     * Update client details - exact same logic as legacy eclient()
     * 
     * @param clientId Client ID
     * @param request  Client details to update
     * @return Updated client details DTO
     */
    ClientDetailsDTO updateClient(String clientId, AddClientRequest request);
}
