package com.go.it.spring.data.controller;

import com.go.it.spring.data.dto.CustomerDto;
import com.go.it.spring.data.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(CustomerController.BASE_PATH)
public class CustomerController {

    public static final String BASE_PATH = "/v1/customers";

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDto> loadAll() {
        return customerService.loadAll();
    }

    @GetMapping("/{id}")
    public CustomerDto loadOne(@PathVariable(name = "id") Long id) {
        return customerService.findById(id);
    }

    @PostMapping
    public CustomerDto save(@RequestBody CustomerDto customerDto) {
        return customerService.save(customerDto);
    }

    @PutMapping
    public CustomerDto update(@RequestBody CustomerDto customerDto) {
        return customerService.update(customerDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }

    @DeleteMapping("/countries/{country}")
    public void deleteByCountry(@PathVariable(name = "country") String country) {
        customerService.deleteByCountry(country);
    }
}
