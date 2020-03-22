package eu.kaszkowiak.demostore.product;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static eu.kaszkowiak.demostore.util.ControllerUtils.validateId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @GetMapping
    @ApiOperation("Get list of products")
    public List<ProductDto> getAll() {
        return mapper.toDto(service.findAll());
    }

    @PostMapping
    @ApiOperation("Create a new product")
    public ProductDto save(@Valid @RequestBody ProductDto product) {
        return mapper.mapExecuteAndMapBack(product, service::save);
    }

    @PutMapping("/{productId}")
    @ApiOperation("Update a product")
    public ProductDto update(
            @Valid @RequestBody ProductDto product,
            @PathVariable Long productId) {
        validateId(product, productId);
        return mapper.mapExecuteAndMapBack(product, service::update);
    }

    @DeleteMapping("/{productId}")
    @ApiOperation("Delete a product")
    public void delete(@PathVariable Long productId) {
        service.delete(productId);
    }
}
