package com.go.it.spring.data.mapper;

import com.go.it.spring.data.dto.ProductDto;
import com.go.it.spring.data.entity.ProductEntity;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class ProductMapper implements Mapper<ProductEntity, ProductDto> {

    @Override
    public ProductDto mapEntityToDto(ProductEntity source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        ProductDto target = new ProductDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        return target;
    }

    @Override
    public ProductEntity mapDtoToEntity(ProductDto source) {
        if (isNull(source)) {
            return null;
        }
        ProductEntity target = new ProductEntity();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        return target;
    }
}
