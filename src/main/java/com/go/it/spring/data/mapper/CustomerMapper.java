package com.go.it.spring.data.mapper;

import com.go.it.spring.data.dto.CustomerDto;
import com.go.it.spring.data.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class CustomerMapper implements Mapper<CustomerEntity, CustomerDto> {

    private final ProductMapper productMapper;

    @Override
    public CustomerDto mapEntityToDto(CustomerEntity source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        CustomerDto target = new CustomerDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setContactName(source.getContactName());
        target.setCountry(source.getCountry());
        target.setProducts(productMapper.mapEntityToDto(source.getProducts()));
        return target;
    }

    @Override
    public CustomerEntity mapDtoToEntity(CustomerDto source) {
        if (isNull(source)) {
            return null;
        }
        CustomerEntity target = new CustomerEntity();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setContactName(source.getContactName());
        target.setCountry(source.getCountry());
        target.setProducts(productMapper.mapDtoToEntity(source.getProducts()));
        return target;
    }
}
