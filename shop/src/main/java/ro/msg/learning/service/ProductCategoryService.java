package ro.msg.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.model.ProductCategory;
import ro.msg.learning.repository.ProductCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategory save(ProductCategory productCategory){
        return productCategoryRepository.save(productCategory);
    }

    public List<ProductCategory> getAll(){
        return productCategoryRepository.findAll();
    }

    public void deleteAll(){
        productCategoryRepository.deleteAll();
    }
}
