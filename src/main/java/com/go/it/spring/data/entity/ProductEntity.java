package com.go.it.spring.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "unit_price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
