package sem.nik.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sem.nik.demo.entiities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    public Product findOneByTitle(String title);

    public Product findOneById(Long id);

    @Modifying
    @Transactional
    @Query("update Product p set p.title=?2, p.price=?3 where p.id=?1")
    public void replaceProductById(Long id, String title, int price);

    @Query("select i from Product i where i.id = ?1")
    public Product find(Long id);

}
