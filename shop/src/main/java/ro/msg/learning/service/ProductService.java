package ro.msg.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.dto.ProductDto;
import ro.msg.learning.model.Product;
import ro.msg.learning.repository.ProductRepository;
import ro.msg.learning.service.exception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto get(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty())
            throw new ProductNotFoundException(id);
        return new ProductDto(product.get());
    }

    public List<ProductDto> getAll(){
        List<ProductDto> productDtos = new ArrayList<>();
        productRepository.findAll().forEach(product -> productDtos.add(new ProductDto(product)));
        return productDtos;
    }

    @Transactional
    public ProductDto save(ProductDto productDto){
        return new ProductDto(productRepository.save(productDto.toEntity()));
    }

    @Transactional
    public ProductDto update(ProductDto productDto, int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty())
            throw new ProductNotFoundException(id);
        productDto.setId(id);
        return new ProductDto(productRepository.save(productDto.toEntity()));
    }

    @Transactional
    public ProductDto delete(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty())
            throw new ProductNotFoundException(id);
        productRepository.deleteById(id);
        return new ProductDto(product.get());
    }
}
