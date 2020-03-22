package eu.kaszkowiak.demostore.product;

import eu.kaszkowiak.demostore.common.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements DtoMapper<ProductE, ProductDto> {

    @Override
    public ProductDto toDto(ProductE product) {
        if (product == null) {
            return null;
        }
        return ProductDto.builder()
                .id(product.getId())
                .creationDate(product.getCreationDate())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    @Override
    public ProductE toEntity(ProductDto product) {
        return ProductE.builder()
                .id(product.getId())
                .creationDate(product.getCreationDate())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
