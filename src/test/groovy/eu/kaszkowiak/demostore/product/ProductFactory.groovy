package eu.kaszkowiak.demostore.product

import org.apache.commons.lang3.RandomStringUtils

trait ProductFactory {
    ProductDto saveProduct() {
        post(url: '/products', content: createProduct(), resultClass: ProductDto) as ProductDto
    }

    static ProductDto createProduct() {
        ProductDto.builder()
                .name(RandomStringUtils.randomPrint(50))
                .price(new BigDecimal(new Random().nextInt(999999999)/10.0))
                .build()
    }
}