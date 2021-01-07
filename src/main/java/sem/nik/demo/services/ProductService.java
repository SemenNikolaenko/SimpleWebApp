package sem.nik.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import sem.nik.demo.entiities.Product;
import sem.nik.demo.repositories.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private static ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) {
        return productRepository.findOneById(id);
    }

    public Product getProductByTitle(String title) {
        return productRepository.findOneByTitle(title);
    }


    public void replaceById(Long id, String title, int price) {
        productRepository.replaceProductById(id, title, price);
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
    public List<Product> sortListOnId(List<Product> list){
       return list.stream().sorted(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
               return o1.getId().compareTo(o2.getId());
            }
        }).collect(Collectors.toList());
    }


}
