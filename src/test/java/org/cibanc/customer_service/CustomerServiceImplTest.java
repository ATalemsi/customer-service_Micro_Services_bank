package org.cibanc.customer_service;

import org.cibanc.customer_service.dto.CustomerDTO;
import org.cibanc.customer_service.exceptions.CustomerNotFoundException;
import org.cibanc.customer_service.mapper.CustomerMapper;
import org.cibanc.customer_service.model.Customer;
import org.cibanc.customer_service.repository.CustomerRepository;
import org.cibanc.customer_service.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setId(1L);
        customer.setNom("John Doe");

        customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setNom("John Doe");
    }

    @Test
    void testAddCustomer() {
        when(customerMapper.toEntity(customerDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.addCustomer(customerDTO);

        assertNotNull(result);
        assertEquals("John Doe", result.getNom());

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = Collections.singletonList(customer);
        List<CustomerDTO> customerDTOs = Collections.singletonList(customerDTO);

        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.toDTOList(customers)).thenReturn(customerDTOs);

        List<CustomerDTO> result = customerService.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getNom());

        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testGetCustomerById_Success() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.getCustomerById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getNom());

        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCustomerById_NotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(1L));

        verify(customerRepository, times(1)).findById(1L);
    }
}
