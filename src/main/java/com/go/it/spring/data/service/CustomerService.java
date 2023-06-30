package com.go.it.spring.data.service;

import com.go.it.spring.data.dto.CustomerDto;
import com.go.it.spring.data.dto.ProductDto;
import com.go.it.spring.data.entity.CustomerEntity;
import com.go.it.spring.data.entity.ProductEntity;
import com.go.it.spring.data.mapper.CustomerMapper;
import com.go.it.spring.data.mapper.ProductMapper;
import com.go.it.spring.data.repository.CustomerEntityRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CustomerEntityRepository customerEntityRepository;
    private final CustomerMapper customerMapper;
    private final ProductMapper productMapper;

    public List<CustomerDto> loadAll() {
        List<CustomerEntity> customerEntities = customerEntityRepository.findAll();
        return customerMapper.mapEntityToDto(customerEntities);
    }

    public CustomerDto findByIdByRepository(Long id) {
        return customerMapper.mapEntityToDto(customerEntityRepository.findCustomerById(id));
    }

    /**
     * Example of {@link NamedParameterJdbcTemplate} usage
     *
     * @param id - customer id
     * @return - requested customer
     */
    public CustomerDto findById(Long id) {
        String query = "SELECT c.id, c.customer_name, c.contact_name, c.country FROM customers c WHERE id=:id";
        return jdbcTemplate.queryForObject(
                query,
                Map.of("id", id),
                (resultSet, index) -> {
                    return CustomerDto.of(
                            resultSet.getLong("id"),
                            resultSet.getString("customer_name"),
                            resultSet.getString("contact_name"),
                            resultSet.getString("country"),
                            null
                    );
                }
        );
    }

    /**
     * Example of {@link NamedParameterJdbcTemplate} usage
     *
     * @param country - country name
     */
    public void deleteByCountry(String country) {
        String query = "DELETE FROM customers c WHERE c.country=:country";
        jdbcTemplate.update(
                query,
                Map.of("country", country)
        );
    }

    public CustomerDto save(CustomerDto customerDto) {
        CustomerEntity saved = customerEntityRepository.save(customerMapper.mapDtoToEntity(customerDto));
        return customerMapper.mapEntityToDto(saved);
    }

    public void delete(Long id) {
        customerEntityRepository.deleteById(id);
    }

    public CustomerDto update(CustomerDto customerDto) {
        if (customerDto.getId() == null) {
            throw new RuntimeException("ID required");
        }
        CustomerEntity existing = customerEntityRepository.findCustomerById(customerDto.getId());
        if (existing == null) {
            throw new RuntimeException("customer not found");
        }
        CustomerEntity customerEntity = customerMapper.mapDtoToEntity(customerDto);
        BeanUtils.copyProperties(customerEntity, existing);
        customerEntityRepository.save(existing);
        return customerMapper.mapEntityToDto(existing);
    }

    @Transactional
    public CustomerDto assignProduct(ProductDto productDto, Long customerId) {
        CustomerEntity customerEntity = customerEntityRepository.findById(customerId)
                .map(customer -> {
                    ProductEntity productEntity = productMapper.mapDtoToEntity(productDto);
                    List<ProductEntity> products = customer.getProducts();
                    products.add(productEntity);
                    productEntity.setCustomer(customer);
                    customerEntityRepository.save(customer);
                    return customer;
                })
                .orElse(null);

        return customerMapper.mapEntityToDto(customerEntity);
    }

    @PostConstruct
    public void construct() {
        log.info("CustomerService construct");
    }

    @PreDestroy
    public void destroy() {
        log.info("CustomerService destroy");
    }
}
