package eu.kaszkowiak.demostore.product

import eu.kaszkowiak.demostore.common.ErrorDto
import eu.kaszkowiak.demostore.common.IntegrationTest
import org.springframework.http.HttpStatus

import java.time.LocalDateTime
import java.time.ZoneOffset

class ProductControllerIT extends IntegrationTest implements ProductFactory {

    def "Should create a product"() {
        when:
        def newProduct = createProduct()
        def response = post(url: '/products', content: newProduct, resultClass: ProductDto) as ProductDto

        then:
        response.id != newProduct.id
        response.creationDate != null
        response.name == newProduct.name
        response.price == newProduct.price
    }

    def "Should not create a product with empty name"() {
        given:
        def newProduct = createProduct()
        newProduct.name = null

        expect:
        post(url: '/products', content: newProduct, status: HttpStatus.BAD_REQUEST.value())
    }

    def "Should list products"() {
        when:

        def result = get(url: '/products', resultClass: ProductDto[]) as List<ProductDto>

        then:
        result.size() > 0
        result*.id != null
        result*.creationDate != null
    }

    def "Should update a product"() {
        given:
        def beforeModification = saveProduct()
        def afterModification = createProduct()

        afterModification.creationDate = LocalDateTime.now().minusMinutes(3)
        afterModification.id = beforeModification.id

        when:
        def result = put(url: "/products/${beforeModification.id}", content: afterModification, resultClass: ProductDto) as ProductDto

        then:
        result.id == beforeModification.id
        // TODO could be improved
        result.creationDate.toEpochSecond(ZoneOffset.UTC) == beforeModification.creationDate.toEpochSecond(ZoneOffset.UTC)
        result.name == afterModification.name
        result.price == afterModification.price
    }

    def "Should not allow updating with null product"() {
        given:
        def product = saveProduct()

        when:
        def result = put(url: "/products/${product.id + 1}", content: product, status: HttpStatus.BAD_REQUEST.value(), resultClass: ErrorDto) as ErrorDto

        then:
        result.error == 'Id mismatch'
    }

    def "Should delete a product"() {
        given:
        def product = saveProduct()

        expect: "Product is soft-deleted"
        delete(url: "/products/${product.id}")

        and: "List of products does not contain the deleted product"
        def list = get(url: '/products', resultClass: ProductDto[]) as List<ProductDto>
        !(list*.id as List<Long>).contains(product.id)

        and: "Should not be able to update deleted product"
        put(url: "/products/${product.id}", content: product, status: HttpStatus.NOT_FOUND.value())
    }

}
