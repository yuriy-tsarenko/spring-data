package com.go.it.spring.data.controller;

import com.go.it.spring.data.dto.CustomerDto;
import com.go.it.spring.data.dto.ProductDto;
import com.go.it.spring.data.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(CustomerControllerV2.BASE_PATH)
public class CustomerControllerV2 {

    public static final String BASE_PATH = "/v2/customers";

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public CustomerDto loadOne(@PathVariable(name = "id") Long id) {
        return customerService.findByIdByRepository(id);
    }

    @PutMapping("/{id}")
    public CustomerDto assignProduct(@RequestBody ProductDto productDto, @PathVariable("id") Long customerId) {
        return customerService.assignProduct(productDto, customerId);
    }
}
