package org.cibanc.customer_service.service;

import org.cibanc.customer_service.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO addCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);

}
