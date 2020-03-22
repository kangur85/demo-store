package eu.kaszkowiak.demostore.product;

import eu.kaszkowiak.demostore.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductService {
    private final ProductRepository repository;

    @Transactional
    public ProductE save(ProductE product) {
        return repository.save(product);
    }

    public List<ProductE> findAll() {
        return repository.findAll();
    }

    @Transactional
    public ProductE update(ProductE product) {
        var dbProduct = repository.getOne(product.getId());
        if (dbProduct == null) {
            throw new NotFoundException(product);
        }
        dbProduct.setName(product.getName());
        dbProduct.setPrice(product.getPrice());
        return repository.save(dbProduct);
    }

    @Transactional
    public void delete(Long productId) {
        var dbProduct = repository.getOne(productId);
        dbProduct.setDeleted(true);
        repository.save(dbProduct);
    }
}
