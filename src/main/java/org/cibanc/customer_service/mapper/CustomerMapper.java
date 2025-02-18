package org.cibanc.customer_service.mapper;

import org.cibanc.customer_service.dto.CustomerDTO;
import org.cibanc.customer_service.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);

    List<CustomerDTO> toDTOList(List<Customer> customers);
}
