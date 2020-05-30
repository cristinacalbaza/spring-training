package ro.msg.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.model.Product;
import ro.msg.learning.repository.ProductRepository;
import ro.msg.learning.service.exception.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    public Product get(int id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product update(Product product, int id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent())
            throw new ProductNotFoundException(id);
        product.setId(id);
        return productRepository.save(product);
    }

    public Product delete(int id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent())
            throw new ProductNotFoundException(id);
        productRepository.deleteById(id);
        return product.get();
    }
}
