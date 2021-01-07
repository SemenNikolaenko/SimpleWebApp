package sem.nik.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sem.nik.demo.entiities.Product;
import sem.nik.demo.repositories.ProductRepository;

import java.math.BigInteger;

@Aspect
@Component
public class CountItem {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Before("execution( public String sem.nik.demo.controllers.ProductController.showOneProduct(..))")
    public void countItem(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        Product product = productRepository.findOneById((Long)objects[0]);
        product.setCountViews(product.getCountViews()+1);
        productRepository.save(product);
    }


}
