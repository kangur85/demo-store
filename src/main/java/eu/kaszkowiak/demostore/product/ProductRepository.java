package eu.kaszkowiak.demostore.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// TODO instead of extending JpaRepository directly, use overriden implementation to make sure soft-deleted
//   objects aren't returned
public interface ProductRepository extends JpaRepository<ProductE, Long> {

    @Query("select p from ProductE p where p.isDeleted = false")
    List<ProductE> findAll();

    //TODO QueryDSL would be better - not enough time for now
    @Query("select p from ProductE p where p.id = :id and p.isDeleted = false")
    ProductE getOne(Long id);
}
