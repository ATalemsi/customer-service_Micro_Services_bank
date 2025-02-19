package org.cibanc.customer_service.service;

import lombok.AllArgsConstructor;
import org.cibanc.customer_service.dto.CustomerDTO;
import org.cibanc.customer_service.exceptions.CustomerNotFoundException;
import org.cibanc.customer_service.mapper.CustomerMapper;
import org.cibanc.customer_service.model.Customer;
import org.cibanc.customer_service.repository.CustomerRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{


    private CustomerRepository customerRepository;


    private CustomerMapper customerMapper;


    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toDTOList(customers);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDTO)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }
}
